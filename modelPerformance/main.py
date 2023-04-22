import mysql.connector
import pandas as pd
from sklearn.tree import DecisionTreeClassifier
import joblib

# Create a connection object using the DataSource class
cnx = mysql.connector.connect(user='root', password='', host='localhost', database='integration3')

# Load the data from the MySQL database
query = '''
SELECT c.id AS competition_id, e.id AS equipe_id, p.victoires, p.defaites, p.nuls, p.but_marque, p.but_encaisses
FROM competition AS c
JOIN competition_equipe AS ce ON c.id = ce.competition_id
JOIN equipe AS e ON ce.equipe_id = e.id
JOIN performance_equipe AS p ON e.performance_e_id = p.id
'''

data = pd.read_sql(query, cnx)

# Load the trained model from file
model = joblib.load('model.joblib')
print("Model hyperparameters:\n", model.get_params())

# Print the feature importances
if isinstance(model, DecisionTreeClassifier):
    print("Feature importances:\n", model.feature_importances_)
# Make predictions for each competition
predicted_winners = []
for competition_id in data['competition_id'].unique():
    competition_data = data[data['competition_id'] == competition_id]
    team_stats = competition_data[['victoires', 'defaites', 'nuls', 'but_marque', 'but_encaisses']].to_numpy()
    winner_id = model.predict(team_stats)[0]
    predicted_winners.append(winner_id)

# Return the predicted winners as a string
print(','.join(map(str, predicted_winners)))

import sqlite3

# Connect to an SQLite database (or create it if it doesn't exist)
conn = sqlite3.connect('mydatabase.db')

# Create a cursor object using the cursor() method
cursor = conn.cursor()


cursor.execute('SELECT game_number FROM game;')
rows = cursor.fetchall()
for game_number_tuple in rows:
    game_number = game_number_tuple[0]
    cursor.execute(
        f"""
        INSERT INTO result (result, team_key, game_key)
        SELECT "{game_number}", team_id, game_id FROM season4, team, game
            WHERE season4.Team=team.team_name
            AND CAST (game.game_number AS TEXT) is {game_number};
        """
        )
    rows = cursor.fetchall()
    print(game_number)
    print(rows)

cursor.execute('SELECT * FROM result;')
rows = cursor.fetchall()
print(rows)

conn.commit()
conn.close()
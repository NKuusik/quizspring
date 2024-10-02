import sqlite3
from pathlib import Path
import subprocess

def import_csv_data(file_path, db_path, file_name):
    db_path = Path(db_path).resolve()
    file_path = Path(file_path).resolve()
    subprocess.run(['sqlite3', 
        str(db_path), 
        '-cmd', 
        '.mode csv', 
        f'.import {str(file_path)} {file_name}'], 
        capture_output=True)


def copy_data(table_number):
    conn = sqlite3.connect('mydatabase.db')
    cursor = conn.cursor()
    cursor.execute('PRAGMA foreign_keys = ON;')
    cursor.execute(
            f"""
            INSERT OR IGNORE INTO season (season_name)
            SELECT DISTINCT season_nr FROM hooaeg_{table_number};
            """)
    cursor.execute(
            f"""
            INSERT OR IGNORE INTO game (game_number, season_key)
            SELECT name, season_id FROM PRAGMA_TABLE_INFO('hooaeg_{table_number}'), season
                WHERE CAST(name AS INTEGER) IS name
                    AND season.season_name='{table_number}';
            """)            

    cursor.execute(
            f"""
                INSERT OR IGNORE INTO team (team_name)
                SELECT Team FROM hooaeg_{table_number};
            """)            

    cursor.execute('SELECT game_number FROM game;')
    rows = cursor.fetchall()
    for game_number_tuple in rows:
        game_number = game_number_tuple[0]
        cursor.execute(
            f"""
            INSERT OR IGNORE INTO result (result, team_key, game_key)
            SELECT "{game_number}", team_id, game_id FROM hooaeg_{table_number}, team, game, season
                WHERE hooaeg_{table_number}.Team=team.team_name
                AND game.season_key=season_id
                AND hooaeg_{table_number}.season_nr=season.season_name
                AND CAST (game.game_number AS TEXT) IS {game_number};
            """
            )

    cursor.execute(f'DROP TABLE hooaeg_{table_number}')
    conn.commit()
    conn.close()


if __name__ == '__main__':
    for x in range(4, 14): 
        import_csv_data(f'db_initialization/resources/hooaeg_{x}.csv', 
                        'mydatabase.db', f'hooaeg_{x}')
        copy_data(x)


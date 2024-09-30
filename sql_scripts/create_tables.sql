CREATE TABLE season(
    season_id INTEGER PRIMARY KEY,
    season_name TEXT NOT NULL UNIQUE
);

CREATE TABLE game(
    game_id INTEGER PRIMARY KEY,
    game_number INTEGER NOT NULL UNIQUE,
    season_key INTEGER NOT NULL,
    FOREIGN KEY (season_key)
        REFERENCES season (season_id)
);

CREATE TABLE team(
    team_id INTEGER PRIMARY KEY,
    team_name TEXT NOT NULL UNIQUE
);

CREATE TABLE result(
    result_id INTEGER PRIMARY KEY,
    result REAL NOT NULL,
    team_key INTEGER NOT NULL,
    game_key INTEGER NOT NULL,
    FOREIGN KEY(team_key)
        REFERENCES team (team_id),
    FOREIGN KEY(game_key)
        REFERENCES game (game_id),
    UNIQUE (team_key, game_key) ON CONFLICT ABORT
);
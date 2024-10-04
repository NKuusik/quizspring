# Readme

## How to initialize the database locally

1. Run the spring application so that tables would be created in liquibase 
2. Run `python3 db_initialization/copy_data.py` from the root of the project 
to transform data in .csv files to database tables.
   - Note that due to differences in pathname conventions, this script does not work in a Windows environment.

## Useful database scripts

Queries the results of one season
```
select sum(result), 
    team_name, season_name from result, team, season, game 
    where team.team_id=result.team_key 
        and season.season_id=game.season_key
        and season_id=5
        and result.game_key=game.game_id
    group by team_name order by sum(result) desc;
```

Queries the results of all seasons
```
select sum(result), team_name, season_name 
    from result, team, season, game 
        where team.team_id=result.team_key 
            and season.season_id=game.season_key
            and result.game_key=game.game_id
    group by team_name, season_name order by cast(season_name as integer), sum(result) desc;
```
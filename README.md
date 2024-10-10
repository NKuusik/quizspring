# Readme

## Notes on running the application

- After the Spring application has been successfully initialized, python script `db_initialization/copy_data.py` is run to add source data from `.csv` files if needed.
  - Note that due to differences in pathname conventions, the python script does not work in a Windows environment.
- Before building a new Docker image, compile the `.jar` file using `./gradlew build`.

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
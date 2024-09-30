INSERT INTO season (season_name)
SELECT DISTINCT season_nr FROM season4;

INSERT INTO game (game_number, season_key)
SELECT name, season_id FROM PRAGMA_TABLE_INFO('season4'), season
    WHERE CAST(name AS INTEGER) IS name
        AND season.season_name='4';

INSERT INTO team (team_name)
SELECT Team FROM season4;


/*
select game_number, team_name, result from result join team on team_id=team_key join game on game_id=game_key;
*/

select sum(result), team_name from result, team where team_id=team_key group by team_name;

select sum(result), team_name from result, team where team_id=team_key group by team_name order by sum(result) desc;
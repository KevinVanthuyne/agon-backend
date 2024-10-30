-- Example data.sql file to inject some initial data into the database instead of having to add each game through the bot.

-- Games
INSERT INTO games (name, description)
VALUES ('Pac-Man',
        'One of the most well-known and popular arcade games of all time. ' ||
        'This maze runner lets you run around, eat pellets and dodge ghosts.'  ||
        'The game was developed by Namco and licensed for distribution in the United States by Midway, in 1980.'),
       ('Mortal Kombat II',
        'A classic 1993 arcade fighting game that expanded on the brutal combat and intense gameplay of the original Mortal Kombat. ' ||
        'Featuring iconic characters, refined controls, new moves, and the infamous Fatalities, ' ||
        'MKII became a defining title in arcade history, known for its dark atmosphere, detailed graphics, and controversial violence.'),
       ('Fish Tales',
        'A 1992 pinball game by Williams, themed around fishing and tall tales. ' ||
        'Known for its colorful artwork, fast-paced gameplay, and unique fishing reel mechanism, ' ||
        'it challenges players to reel in big catches, tell outrageous fish stories, and rack up high scores. ' ||
        'Its humor, challenging ramps, and catchy soundtrack have made it a beloved classic in the pinball community.'),
       ('Arkanoid',
        'Arkanoid is a 1986 arcade classic by Taito, reinventing the block-breaking genre with power-ups, ' ||
        'diverse level designs, and a sci-fi storyline. Players control the Vaus spacecraft to bounce a ball, ' ||
        'break bricks, and progress through increasingly challenging levels, aiming to defeat the villainous DOH. ' ||
        'Its addictive gameplay and smooth controls made Arkanoid a defining hit in arcades worldwide.'),
       ('Avengers: Infinity Quest',
        'The 2020 pinball machine by Stern, inspired by Marvel''s Avengers universe. ' ||
        'Players join iconic heroes like Iron Man, Thor, and Doctor Strange on a mission to collect the Infinity Stones and battle Thanos. ' ||
        'Featuring dynamic ramps, interactive multiball modes, and dazzling artwork, the game combines intense pinball action ' ||
        'with Marvel’s epic storyline, delivering an immersive experience filled with superhero thrills and challenges.')
ON CONFLICT DO NOTHING;

-- Game styles
INSERT INTO game_styles(game_id, background_color, header_image, cabinet_image)
VALUES
    -- Pac Man
    (1,
     'none',
     'https://i.ebayimg.com/images/g/lYUAAOSw1Ylf8ede/s-l1200.webp',
     'https://www.joystixgames.com/wp-content/uploads/2024/03/Pac-Man-arcade-game-at-Joystix-resized.jpg'),
    -- Mortal Kombat II
    (2,
     'none',
     'https://arcademarquee.com/wp-content/uploads/2015/02/mortal-kombat-II_marquee-scaled.jpg',
     'https://www.joystixgames.com/wp-content/uploads/2024/02/Mortal-Kombat-2-game-at-Joystix.jpg'),
    -- Fish Tales
    (3,
     'none',
     'https://classicplayfields.com/wp-content/uploads/2019/04/Fish-Tales-Backglass.jpg',
     'https://www.lyon-flipper.com/images/f/e/a/t/u/featured-flipper-williams-electronics-games-fish-tales-1-90c193ab.jpg'),
    -- Arkanoid
    (4,
     'none',
     'https://i0.wp.com/arcademarquee.com/wp-content/uploads/2014/12/arkanoid_marquee.jpg?fit=1199%2C369&ssl=1',
     'https://www.arcade-museum.com/images-game/19/arkanoid-19173.jpg'),
    -- Avengers
    (5,
     'none',
     'https://kartingmadness.com.au/wp-content/uploads/2022/05/Avengers-Infinity-Quest.jpeg',
     'https://sternpinball.com/wp-content/uploads/2020/08/Avengers-Pro-Cabinet-LF-fadskn3-scaled.jpg')
ON CONFLICT DO NOTHING;

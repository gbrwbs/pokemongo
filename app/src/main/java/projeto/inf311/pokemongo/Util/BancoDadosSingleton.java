package projeto.inf311.pokemongo.Util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import projeto.inf311.pokemongo.R;

/**
 * Created by vanessa on 04/05/17.
 */

public final class BancoDadosSingleton {
    private final String NOME_BANCO = "pokemon_go_db";
    private static BancoDadosSingleton INSTANCE = new BancoDadosSingleton();
    protected SQLiteDatabase db;
    private String[] SCRIPT_DATA_BASE_CREATE = new String[] {
            "CREATE TABLE pokemon (" +
            "idPokemon INTEGER PRIMARY KEY," +
            "nome TEXT NOT NULL,"+
            "categoria TEXT NOT NULL," +
            "foto INTEGER NOT NULL," +
            "icone INTEGER NOT NULL);" ,

            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (1, 'Bulbasaur', 'I'," + R.drawable.p1 + "," + R.drawable.i1 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (2, 'Ivysaur', 'I'," + R.drawable.p2 +"," + R.drawable.i2 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (3, 'Venusaur', 'R'," + R.drawable.p3+ "," + R.drawable.i3 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (4, 'Charmander', 'I'," + R.drawable.p4 + "," + R.drawable.i4 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (5, 'Charmeleon', 'I'," + R.drawable.p5 + "," + R.drawable.i5 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (6, 'Charizard', 'R'," + R.drawable.p6 + "," + R.drawable.i6 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (7, 'Squirtle', 'I'," + R.drawable.p7 + "," + R.drawable.i7 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (8, 'Wartortle', 'I'," + R.drawable.p8 + "," + R.drawable.i8 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (9, 'Blastoise', 'R'," + R.drawable.p9 + "," + R.drawable.i9 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (10, 'Caterpie', 'C'," + R.drawable.p10 + "," + R.drawable.i10 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (11, 'Metapod', 'C'," + R.drawable.p11 + "," + R.drawable.i11 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (12, 'Butterfree', 'I'," + R.drawable.p12 + "," + R.drawable.i12 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (13, 'Weedle', 'C'," + R.drawable.p13 + "," + R.drawable.i13 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (14, 'Kakuna', 'C'," + R.drawable.p14 + "," + R.drawable.i14 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (15, 'Beedrill', 'I'," + R.drawable.p15 + "," + R.drawable.i15 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (16, 'Pidgey', 'C'," + R.drawable.p16 + "," + R.drawable.i16 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (17, 'Pidgeotto', 'I'," + R.drawable.p17 + "," + R.drawable.i17 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (18, 'Pidgeot', 'R'," + R.drawable.p18 + "," +  R.drawable.i18 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (19, 'Rattata', 'C'," + R.drawable.p19 + "," +  R.drawable.i19 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (20, 'Raticate', 'I'," + R.drawable.p20 + "," +  R.drawable.i20 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (21, 'Spearow', 'C'," + R.drawable.p21 + "," +  R.drawable.i21 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (22, 'Fearow', 'I'," + R.drawable.p22 + "," +  R.drawable.i22 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (23, 'Ekans', 'C'," + R.drawable.p23 + "," +  R.drawable.i23 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (24, 'Arbok', 'I'," + R.drawable.p24 + "," +  R.drawable.i24 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (25, 'Pikachu', 'C'," + R.drawable.p25 + "," +  R.drawable.i25 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (26, 'Raichu', 'I'," + R.drawable.p26 + "," +  R.drawable.i26 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (27, 'Sandshrew', 'C'," + R.drawable.p27 + "," +  R.drawable.i27 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (28, 'Sandslash', 'I'," + R.drawable.p28 + "," +  R.drawable.i28 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (29, 'Nidoran Femea', 'C', " + R.drawable.p29 + "," +  R.drawable.i29 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (30, 'Nidorina', 'I', " + R.drawable.p30 + "," +  R.drawable.i30 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (31, 'Nidoqueen', 'R'," + R.drawable.p31 + "," +  R.drawable.i31 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (32, 'Nidoran Macho', 'C'," + R.drawable.p32 + "," +  R.drawable.i32 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (33, 'Nidorino', 'I'," + R.drawable.p33 + "," +  R.drawable.i33 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (34, 'Nidoking', 'R'," + R.drawable.p34 + "," +  R.drawable.i34 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (35, 'Clefairy', 'I'," + R.drawable.p35 + "," +  R.drawable.i35 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (36, 'Clefable', 'R'," + R.drawable.p36 + "," +  R.drawable.i36 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (37, 'Vulpix', 'C'," + R.drawable.p37 + "," +  R.drawable.i37 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (38, 'Ninetales', 'R'," + R.drawable.p38 + "," +  R.drawable.i38 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (39, 'Jigglypuff', 'C'," + R.drawable.p39 + "," +  R.drawable.i39 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (40, 'Wigglytuff', 'R'," + R.drawable.p40 + "," +  R.drawable.i40 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (41, 'Zubat', 'C'," + R.drawable.p41 + "," +  R.drawable.i41 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (42, 'Golbat', 'I'," + R.drawable.p42 + "," +  R.drawable.i42 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (43, 'Oddish', 'C'," + R.drawable.p43 + "," +  R.drawable.i43 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (44, 'Gloom', 'C'," + R.drawable.p44 + "," +  R.drawable.i44 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (45, 'Vileplume', 'I'," + R.drawable.p45 + "," + R.drawable.i45 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (46, 'Paras', 'C'," + R.drawable.p46 + "," +  R.drawable.i46 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (47, 'Parasect', 'I'," + R.drawable.p47 + "," +  R.drawable.i47 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (48, 'Venonat', 'C'," + R.drawable.p48 + "," +  R.drawable.i48 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (49, 'Venomoth', 'I'," + R.drawable.p49 + "," +  R.drawable.i49 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (50, 'Diglett', 'C'," + R.drawable.p50 + "," +  R.drawable.i50 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (51, 'Dugtrio', 'I'," + R.drawable.p51 + "," +  R.drawable.i51 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (52, 'Meowth', 'C'," + R.drawable.p52 + "," +  R.drawable.i52 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (53, 'Persian', 'I'," + R.drawable.p53 + "," +  R.drawable.i53 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (54, 'Psyduck', 'C'," + R.drawable.p54 + "," +  R.drawable.i54 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (55, 'Golduck', 'I'," + R.drawable.p55 + "," +  R.drawable.i55 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (56, 'Mankey', 'C'," + R.drawable.p56 + "," +  R.drawable.i56 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (57, 'Primeape', 'I'," + R.drawable.p57 + "," +  R.drawable.i57 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (58, 'Growlithe', 'C'," + R.drawable.p58 + "," +  R.drawable.i58 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (59, 'Arcanine', 'R'," + R.drawable.p59 + "," +  R.drawable.i59 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (60, 'Poliwag', 'C'," + R.drawable.p60 + "," +  R.drawable.i60 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (61, 'Poliwhril', 'C', " + R.drawable.p61 + "," +  R.drawable.i61 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (62, 'Poliwrath', 'R'," + R.drawable.p62 + "," +  R.drawable.i62 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (63, 'Abra', 'C'," + R.drawable.p63 + "," +  R.drawable.i63 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (64, 'Kadabra', 'I'," + R.drawable.p64 + "," +  R.drawable.i64 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (65, 'Alakazam', 'R'," + R.drawable.p65 + "," +  R.drawable.i65 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (66, 'Machop', 'C'," + R.drawable.p66 + "," +  R.drawable.i66 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (67, 'Machoke', 'I'," + R.drawable.p67 + "," +  R.drawable.i67 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (68, 'Machamp', 'R'," + R.drawable.p68 + "," +  R.drawable.i68 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (69, 'Bellsprout', 'C'," + R.drawable.p69 + "," +  R.drawable.i69 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (70, 'Weepinbell', 'I'," + R.drawable.p70 + "," +  R.drawable.i70 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (71, 'Victreebel', 'R'," + R.drawable.p71 + "," +  R.drawable.i71 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (72, 'Tentacool', 'C'," + R.drawable.p72 + "," +  R.drawable.i72 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (73, 'Tentacruel', 'I'," + R.drawable.p73 + "," +  R.drawable.i73 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (74, 'Geodude', 'C'," + R.drawable.p74 + "," +  R.drawable.i74 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (75, 'Graveler', 'I'," + R.drawable.p75 + "," +  R.drawable.i75 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (76, 'Golem', 'R'," + R.drawable.p76 + "," +  R.drawable.i76 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (77, 'Ponyta', 'C'," + R.drawable.p77 + "," +  R.drawable.i77 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (78, 'Rapidash', 'I'," + R.drawable.p78 + "," +  R.drawable.i78 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (79, 'Slowpoke', 'C'," + R.drawable.p79 + "," +  R.drawable.i79 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (80, 'Slowbro', 'I'," + R.drawable.p80 + "," +  R.drawable.i80 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (81, 'Magnemite', 'C'," + R.drawable.p81 + "," +  R.drawable.i81 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (82, 'Magneton', 'I'," + R.drawable.p82 + "," +  R.drawable.i82 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (83, 'Farfetch''d', 'C'," + R.drawable.p83 + "," +  R.drawable.i83 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (84, 'Doduo', 'C'," + R.drawable.p84 + "," +  R.drawable.i84 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (85, 'Dodrio', 'I'," + R.drawable.p85 + "," +  R.drawable.i85 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (86, 'Seel', 'C'," + R.drawable.p86 + "," +  R.drawable.i86 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (87, 'Dewgong', 'R'," + R.drawable.p87 + "," +  R.drawable.i87 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (88, 'Grimer', 'C'," + R.drawable.p88 + "," +  R.drawable.i88 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (89, 'Muk', 'R'," + R.drawable.p89 + "," +  R.drawable.i89 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (90, 'Shellder', 'C'," + R.drawable.p90 + "," +  R.drawable.i90 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (91, 'Cloyster', 'R'," + R.drawable.p91 + "," +  R.drawable.i91 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (92, 'Gastly', 'C'," + R.drawable.p92 + "," +  R.drawable.i92 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (93, 'Haunter', 'I'," + R.drawable.p93 + "," +  R.drawable.i93 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (94, 'Gengar', 'R'," + R.drawable.p94 + "," +  R.drawable.i94 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (95, 'Onix', 'C'," + R.drawable.p95 + "," +  R.drawable.i95 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (96, 'Drowzee', 'C'," + R.drawable.p96 + "," +  R.drawable.i96 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (97, 'Hypno', 'I'," + R.drawable.p97 + "," +  R.drawable.i97 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (98, 'Krabby', 'C'," + R.drawable.p98 + "," +  R.drawable.i98 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (99, 'Kingler', 'I'," + R.drawable.p99 + "," +  R.drawable.i99 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (100, 'Voltorb', 'C'," + R.drawable.p100 + "," +  R.drawable.i100 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (101, 'Electrode', 'I'," + R.drawable.p101 + "," +  R.drawable.i101 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (102, 'Exeggcute', 'I'," + R.drawable.p102 + "," +  R.drawable.i102 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (103, 'Exeggutor', 'R'," + R.drawable.p103 + "," +  R.drawable.i103 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (104, 'Cubone', 'C'," + R.drawable.p104 + "," +  R.drawable.i104 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (105, 'Marowak', 'I'," + R.drawable.p105 + "," +  R.drawable.i105 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (106, 'Hitmonlee', 'I'," + R.drawable.p106 + "," +  R.drawable.i106 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (107, 'Hitmonchan', 'I'," + R.drawable.p107 + "," +  R.drawable.i107 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (108, 'Lickitung', 'I'," + R.drawable.p108 + "," +  R.drawable.i108 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (109, 'Koffing', 'C'," + R.drawable.p109 + "," +  R.drawable.i109 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (110, 'Weezing', 'I'," + R.drawable.p110 + "," +  R.drawable.i110 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (111, 'Rhyhorn', 'C'," + R.drawable.p111 + "," +  R.drawable.i111 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (112, 'Rhydon', 'I'," + R.drawable.p112 + "," +  R.drawable.i112 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (113, 'Chansey', 'I'," + R.drawable.p113 + "," +  R.drawable.i113 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (114, 'Tangela', 'I'," + R.drawable.p114 + "," +  R.drawable.i114 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (115, 'Kangaskhan', 'I'," + R.drawable.p115 + "," +  R.drawable.i115 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (116, 'Horsea', 'C'," + R.drawable.p116 + "," +  R.drawable.i116 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (117, 'Seadra', 'I'," + R.drawable.p117 + "," +  R.drawable.i117 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (118, 'Goldeen', 'C'," + R.drawable.p118 + "," +  R.drawable.i118 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (119, 'Seaking', 'I'," + R.drawable.p119 + "," +  R.drawable.i119 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (120, 'Staryu', 'C'," + R.drawable.p120 + "," +  R.drawable.i120 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (121, 'Starmie', 'I'," + R.drawable.p121 + "," +  R.drawable.i121 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (122, 'Mr. Mime', 'I'," + R.drawable.p122 + "," +  R.drawable.i122 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (123, 'Scyther', 'I'," + R.drawable.p123 + "," +  R.drawable.i123 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (124, 'Jynx', 'I'," + R.drawable.p124 + "," +  R.drawable.i124 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (125, 'Electabuzz', 'I'," + R.drawable.p125 + "," + R.drawable.i125 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (126, 'Magmar', 'I'," + R.drawable.p126 + "," +  R.drawable.i126 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (127, 'Pinsir', 'I'," + R.drawable.p127 + "," +  R.drawable.i127 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (128, 'Tauros', 'I'," + R.drawable.p128 + "," +  R.drawable.i128 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (129, 'Magikarp', 'C'," + R.drawable.p129 + "," +  R.drawable.i129 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (130, 'Gyarados', 'I'," + R.drawable.p130 + "," +  R.drawable.i130 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (131, 'Lapras', 'I'," + R.drawable.p131 + "," +  R.drawable.i131 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (132, 'Ditto', 'I'," + R.drawable.p132 + "," +  R.drawable.i132 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (133, 'Eevee', 'I'," + R.drawable.p133 + "," +  R.drawable.i133 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (134, 'Vaporeon', 'R'," + R.drawable.p134 + "," +  R.drawable.i134 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (135, 'Jolteon', 'R'," + R.drawable.p135 + "," +  R.drawable.i135 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (136, 'Flareon', 'R'," + R.drawable.p136 + "," +  R.drawable.i136 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (137, 'Porygon', 'R'," + R.drawable.p137 + "," +  R.drawable.i137 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (138, 'Omanyte', 'R'," + R.drawable.p138 + "," +  R.drawable.i138 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (139, 'Omastar', 'R'," +  R.drawable.p139 + "," +  R.drawable.i139 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (140, 'Kabuto', 'R'," +  R.drawable.p140 + "," +  R.drawable.i140 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (141, 'Kabutops', 'R'," +  R.drawable.p141 + "," +  R.drawable.i141 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (142, 'Aerodactyl', 'R'," +  R.drawable.p142 + "," +  R.drawable.i142 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (143, 'Snorlax', 'I'," +  R.drawable.p143 + "," + R.drawable.i143 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (144, 'Articuno', 'L'," +  R.drawable.p144 + "," +  R.drawable.i144 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (145, 'Zapdos', 'L'," +  R.drawable.p145 + "," +  R.drawable.i145 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (146, 'Moltres', 'L'," +  R.drawable.p146 + "," +  R.drawable.i146 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (147, 'Dratini', 'I'," +  R.drawable.p147 + "," +  R.drawable.i147 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (148, 'Dragonair', 'I'," +  R.drawable.p148 + "," +  R.drawable.i148 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (149, 'Dragonite', 'R'," +  R.drawable.p149 + "," +  R.drawable.i149 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (150, 'Mewtwo', 'L'," +  R.drawable.p150 + "," +  R.drawable.i150 + ");",
            "INSERT INTO pokemon (idPokemon, nome, categoria, foto, icone) VALUES (151, 'Mew', 'L'," + R.drawable.p151 + "," + R.drawable.i151 + " );",

            "CREATE TABLE tipo ("+
            "idTipo INTEGER PRIMARY KEY,"+
            "nome TEXT NOT NULL );",

            " INSERT INTO tipo (idTipo, nome) VALUES (1, 'Normal');",
            " INSERT INTO tipo (idTipo, nome) VALUES (2, 'Fire');",
            " INSERT INTO tipo (idTipo, nome) VALUES (3, 'Fighting');",
            " INSERT INTO tipo (idTipo, nome) VALUES (4, 'Water');",
            " INSERT INTO tipo (idTipo, nome) VALUES (5, 'Flying');",
            " INSERT INTO tipo (idTipo, nome) VALUES (6, 'Grass');",
            " INSERT INTO tipo (idTipo, nome) VALUES (7, 'Poison');",
            " INSERT INTO tipo (idTipo, nome) VALUES (8, 'Electric');",
            " INSERT INTO tipo (idTipo, nome) VALUES (9, 'Ground');",
            " INSERT INTO tipo (idTipo, nome) VALUES (10, 'Psychic');",
            " INSERT INTO tipo (idTipo, nome) VALUES (11, 'Rock');",
            " INSERT INTO tipo (idTipo, nome) VALUES (12, 'Ice');",
            " INSERT INTO tipo (idTipo, nome) VALUES (13, 'Bug');",
            " INSERT INTO tipo (idTipo, nome) VALUES (14, 'Dragon');",
            " INSERT INTO tipo (idTipo, nome) VALUES (15, 'Ghost');",
            " INSERT INTO tipo (idTipo, nome) VALUES (16, 'Dark');",
            " INSERT INTO tipo (idTipo, nome) VALUES (17, 'Steel');",
            " INSERT INTO tipo (idTipo, nome) VALUES (18, 'Fairy');",

            "CREATE TABLE pokemontipo ("+
                    "idPokemon INTEGER NOT NULL,"+
                    "idTipo INTEGER NOT NULL,"+
                    "PRIMARY KEY  (idPokemon,idTipo),"+
                    "CONSTRAINT fk_pokemontipo_pokemon FOREIGN KEY (idPokemon) REFERENCES pokemon (idPokemon),"+
                    "CONSTRAINT fk_pokemontipo_tipo FOREIGN KEY (idTipo) REFERENCES tipo (idTipo)"+
                    ");",

            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (16, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (17, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (18, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (19, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (20, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (21, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (22, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (39, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (40, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (52, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (53, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (83, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (84, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (85, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (108, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (113, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (115, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (128, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (132, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (133, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (137, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (143, 1);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (4, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (5, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (6, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (37, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (38, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (58, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (59, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (77, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (78, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (126, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (136, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (146, 2);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (56, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (57, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (62, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (66, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (67, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (68, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (106, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (107, 3);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (7, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (8, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (9, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (54, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (55, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (60, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (61, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (62, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (72, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (73, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (79, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (80, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (86, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (87, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (90, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (91, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (98, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (99, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (116, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (117, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (118, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (119, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (120, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (121, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (129, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (130, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (131, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (134, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (138, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (139, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (140, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (141, 4);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (6, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (12, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (16, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (17, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (18, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (21, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (22, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (41, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (42, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (83, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (84, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (85, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (123, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (130, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (142, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (144, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (145, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (146, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (149, 5);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (1, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (2, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (3, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (43, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (44, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (45, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (46, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (47, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (69, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (70, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (71, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (102, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (103, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (114, 6);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (1, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (2, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (3, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (13, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (14, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (15, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (23, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (24, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (29, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (30, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (31, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (32, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (33, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (34, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (41, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (42, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (43, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (44, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (45, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (48, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (49, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (69, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (70, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (71, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (72, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (73, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (88, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (89, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (92, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (93, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (94, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (109, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (110, 7);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (25, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (26, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (81, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (82, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (100, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (101, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (125, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (135, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (145, 8);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (27, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (28, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (31, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (34, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (50, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (51, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (74, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (75, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (76, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (95, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (104, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (105, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (111, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (112, 9);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (63, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (64, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (65, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (79, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (80, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (96, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (97, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (102, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (103, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (121, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (122, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (124, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (150, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (151, 10);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (74, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (75, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (76, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (95, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (111, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (112, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (138, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (139, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (140, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (141, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (142, 11);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (87, 12);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (91, 12);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (124, 12);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (131, 12);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (144, 12);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (10, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (11, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (12, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (13, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (14, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (15, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (46, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (47, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (48, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (49, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (123, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (127, 13);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (147, 14);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (148, 14);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (149, 14);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (92, 15);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (93, 15);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (94, 15);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (81, 17);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (82, 17);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (35, 18);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (36, 18);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (39, 18);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (40, 18);",
            " INSERT INTO pokemontipo (idPokemon, idTipo) VALUES (122, 18);",

            "CREATE TABLE usuario ("+
                    "login TEXT PRIMARY KEY,"+
                    "senha TEXT NOT NULL,"+
                    "nome TEXT NOT NULL,"+
                    "sexo TEXT NOT NULL,"+
                    "foto TEXT,"+
                    "dtCadastro TEXT NOT NULL,"+
                    "temSessao TEXT NOT NULL );",

            "CREATE TABLE pokemonusuario ("+
                    "login TEXT NOT NULL,"+
                    "idPokemon INTEGER NOT NULL,"+
                    "latitude REAL NOT NULL,"+
                    "longitude REAL NOT NULL,"+
                    "dtCaptura TEXT NOT NULL,"+
                    "PRIMARY KEY  (login,idPokemon,dtCaptura),"+
                    "CONSTRAINT fk_usuariopokemon_login FOREIGN KEY (login) REFERENCES usuario (login),"+
                    "CONSTRAINT fk_usuariopokemon_pokemon FOREIGN KEY (idPokemon) REFERENCES pokemon (idPokemon)"+
                    ");"

             };

    private BancoDadosSingleton(){
        Context ctx = MyApp.getAppContext();

        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);

        Cursor c = buscar("sqlite_master", null, "type = 'table'", "");

        if(c.getCount() == 1){
            for(int i = 0; i < SCRIPT_DATA_BASE_CREATE.length; i++){
                db.execSQL(SCRIPT_DATA_BASE_CREATE[i]);
            }
            Log.i("BANCO_DADOS", "Criou tabelas do banco e as populou.");
        }
        c.close();
        Log.i("BANCO_DADOS", "Abriu conexão com o banco.");

    }

    public static BancoDadosSingleton getINSTANCE() {
        return INSTANCE;
    }

    public long inserir(String tabelas, ContentValues valores){
        long id = db.insert(tabelas, null, valores);
        Log.i("BANCO_DADOS", "Cadastrou registro com o id [" + id + "]");
        return id;
    }

    public int atualizar(String tabela, ContentValues valores, String where) {
        int count = db.update(tabela, valores, where, null);
        Log.i("BANCO_DADOS", "Atualizou [" + count + "] registros");
        return count;
    }

    public int deletar(String tabela, String where) {
        int count = db.delete(tabela, where, null);
        Log.i("BANCO_DADOS", "Deletou [" + count + "] registros");
        return count;
    }

    public Cursor buscar(String tabelas, String colunas[], String where, String orderBy) {
        Cursor c;
        if(!where.equals(""))
            c = db.query(tabelas, colunas, where, null, null, null, orderBy);
        else
            c = db.query(tabelas, colunas, null, null, null, null, orderBy);
        Log.i("BANCO_DADOS", "Realizou uma busca e retornou [" + c.getCount() + "] registros.");
        return c;
    }

    public void abrir(Context ctx) {
    // Abre o banco de dados já existente
        db = ctx.openOrCreateDatabase(NOME_BANCO, Context.MODE_PRIVATE, null);
        Log.i("BANCO_DADOS", "Abriu conexão com o banco.");
    }

    public void fechar() {
        if (db != null) {
            db.close();
            Log.i("BANCO_DADOS", "Fechou conexão com o Banco.");
        }
    }

    public void drop(String tabela){
        db.execSQL("DROP TABLE IF EXISTS " + tabela);
        Log.i("BANCO_DADOS", "Dropou.");
    }

}
/*
 * Copyright (C) 2015, Peter Jakubčo
 * KISS, YAGNI, DRY
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.emustudio.zilogZ80.impl;

public class EmulatorTables {
    
    public final static short[] SIGN_ZERO_CARRY_TABLE = new short[]{
        64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 65, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 129, 129, 129, 129, 129, 129,
        129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129,
        129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129,
        129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129,
        129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129, 129,
        129, 129
    };

    public final static short[] SIGN_ZERO_TABLE = new short[]{
        64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128
    };

    public final static short[] INC_TABLE = new short[] {
        80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 148, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 144, 128, 128, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 144, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 144, 128, 128, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 144, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 144, 128,
        128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 144, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128,
        144, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128, 128
    };
            
    public final static short[] DEC_TABLE = new short[]{
        82, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
        18, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
        18, 18, 18, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18,
        18, 18, 18, 18, 18, 2, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 2, 18, 18, 18, 18, 18, 18, 18, 18,
        18, 18, 18, 18, 18, 18, 18, 6, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130, 146, 146, 146, 146, 146, 146,
        146, 146, 146, 146, 146, 146, 146, 146, 146, 130, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130, 146, 146, 146, 146,
        146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130, 146, 146,
        146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130,
        146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 146, 130
    };

    public final static int[] DAA_NOT_H_NOT_C_TABLE = new int[] {
            17408, 1, 2, 1027, 4, 1029, 1030, 7, 8, 1033, 4112, 5137, 5138, 4115, 5140, 4117, 16, 1041, 1042, 19, 1044, 21, 22, 1047, 1048, 25, 4128, 5153, 5154, 4131, 5156, 4133, 32, 1057, 1058, 35, 1060, 37, 38, 1063, 1064, 41, 5168, 4145, 4146, 5171, 4148, 5173, 1072, 49, 50, 1075, 52, 1077, 1078, 55, 56, 1081, 4160, 5185, 5186, 4163, 5188, 4165, 64, 1089, 1090, 67, 1092, 69, 70, 1095, 1096, 73, 5200, 4177, 4178, 5203, 4180, 5205, 1104, 81, 82, 1107, 84, 1109, 1110, 87, 88, 1113, 5216, 4193, 4194, 5219, 4196, 5221, 1120, 97, 98, 1123, 100, 1125, 1126, 103, 104, 1129, 4208, 5233, 5234, 4211, 5236, 4213, 112, 1137, 1138, 115, 1140, 117, 118, 1143, 1144, 121, 36992, 38017, 38018, 36995, 38020, 36997, 32896, 33921, 33922, 32899, 33924, 32901, 32902, 33927, 33928, 32905, 38032, 37009, 37010, 38035, 37012, 38037, 33936, 32913, 32914, 33939, 32916, 33941, 33942, 32919, 32920, 33945, 21760, 4353, 4354, 5379, 4356, 5381, 17664, 257, 258, 1283, 260, 1285, 1286, 263, 264, 1289, 4368, 5393, 5394, 4371, 5396, 4373, 272, 1297, 1298, 275, 1300, 277, 278, 1303, 1304, 281, 4384, 5409, 5410, 4387, 5412, 4389, 288, 1313, 1314, 291, 1316, 293, 294, 1319, 1320, 297, 5424, 4401, 4402, 5427, 4404, 5429, 1328, 305, 306, 1331, 308, 1333, 1334, 311, 312, 1337, 4416, 5441, 5442, 4419, 5444, 4421, 320, 1345, 1346, 323, 1348, 325, 326, 1351, 1352, 329, 5456, 4433, 4434, 5459, 4436, 5461, 1360, 337, 338, 1363, 340, 1365, 1366, 343, 344, 1369, 21760, 4353, 4354, 5379, 4356, 5381
    };
            
    public final static int[] DAA_H_NOT_C_TABLE = new int[] {
            1030, 7, 8, 1033, 1034, 11, 1036, 13, 14, 1039, 4112, 5137, 5138, 4115, 5140, 4117, 22, 1047, 1048, 25, 26, 1051, 28, 1053, 1054, 31, 4128, 5153, 5154, 4131, 5156, 4133, 38, 1063, 1064, 41, 42, 1067, 44, 1069, 1070, 47, 5168, 4145, 4146, 5171, 4148, 5173, 1078, 55, 56, 1081, 1082, 59, 1084, 61, 62, 1087, 4160, 5185, 5186, 4163, 5188, 4165, 70, 1095, 1096, 73, 74, 1099, 76, 1101, 1102, 79, 5200, 4177, 4178, 5203, 4180, 5205, 1110, 87, 88, 1113, 1114, 91, 1116, 93, 94, 1119, 5216, 4193, 4194, 5219, 4196, 5221, 1126, 103, 104, 1129, 1130, 107, 1132, 109, 110, 1135, 4208, 5233, 5234, 4211, 5236, 4213, 118, 1143, 1144, 121, 122, 1147, 124, 1149, 1150, 127, 36992, 38017, 38018, 36995, 38020, 36997, 32902, 33927, 33928, 32905, 32906, 33931, 32908, 33933, 33934, 32911, 38032, 37009, 37010, 38035, 37012, 38037, 33942, 32919, 32920, 33945, 33946, 32923, 33948, 32925, 32926, 33951, 21760, 4353, 4354, 5379, 4356, 5381, 1286, 263, 264, 1289, 1290, 267, 1292, 269, 270, 1295, 4368, 5393, 5394, 4371, 5396, 4373, 278, 1303, 1304, 281, 282, 1307, 284, 1309, 1310, 287, 4384, 5409, 5410, 4387, 5412, 4389, 294, 1319, 1320, 297, 298, 1323, 300, 1325, 1326, 303, 5424, 4401, 4402, 5427, 4404, 5429, 1334, 311, 312, 1337, 1338, 315, 1340, 317, 318, 1343, 4416, 5441, 5442, 4419, 5444, 4421, 326, 1351, 1352, 329, 330, 1355, 332, 1357, 1358, 335, 5456, 4433, 4434, 5459, 4436, 5461, 1366, 343, 344, 1369, 1370, 347, 1372, 349, 350, 1375, 21760, 4353, 4354, 5379, 4356, 5381
    };
            
    public final static int[] DAA_NOT_H_C_TABLE = new int[] {
            1120, 97, 98, 1123, 100, 1125, 1126, 103, 104, 1129, 4208, 5233, 5234, 4211, 5236, 4213, 112, 1137, 1138, 115, 1140, 117, 118, 1143, 1144, 121, 36992, 38017, 38018, 36995, 38020, 36997, 32896, 33921, 33922, 32899, 33924, 32901, 32902, 33927, 33928, 32905, 38032, 37009, 37010, 38035, 37012, 38037, 33936, 32913, 32914, 33939, 32916, 33941, 33942, 32919, 32920, 33945, 38048, 37025, 37026, 38051, 37028, 38053, 33952, 32929, 32930, 33955, 32932, 33957, 33958, 32935, 32936, 33961, 37040, 38065, 38066, 37043, 38068, 37045, 32944, 33969, 33970, 32947, 33972, 32949, 32950, 33975, 33976, 32953, 38080, 37057, 37058, 38083, 37060, 38085, 33984, 32961, 32962, 33987, 32964, 33989, 33990, 32967, 32968, 33993, 37072, 38097, 38098, 37075, 38100, 37077, 32976, 34001, 34002, 32979, 34004, 32981, 32982, 34007, 34008, 32985, 37088, 38113, 38114, 37091, 38116, 37093, 32992, 34017, 34018, 32995, 34020, 32997, 32998, 34023, 34024, 33001, 38128, 37105, 37106, 38131, 37108, 38133, 34032, 33009, 33010, 34035, 33012, 34037, 34038, 33015, 33016, 34041, 21760, 4353, 4354, 5379, 4356, 5381, 17664, 257, 258, 1283, 260, 1285, 1286, 263, 264, 1289, 4368, 5393, 5394, 4371, 5396, 4373, 272, 1297, 1298, 275, 1300, 277, 278, 1303, 1304, 281, 4384, 5409, 5410, 4387, 5412, 4389, 288, 1313, 1314, 291, 1316, 293, 294, 1319, 1320, 297, 5424, 4401, 4402, 5427, 4404, 5429, 1328, 305, 306, 1331, 308, 1333, 1334, 311, 312, 1337, 4416, 5441, 5442, 4419, 5444, 4421, 320, 1345, 1346, 323, 1348, 325, 326, 1351, 1352, 329, 5456, 4433, 4434, 5459, 4436, 5461, 1360, 337, 338, 1363, 340, 1365, 1366, 343, 344, 1369, 5472, 4449, 4450, 5475, 4452, 5477
    };

    public final static int[] DAA_H_C_TABLE = new int[] {
            1126, 103, 104, 1129, 1130, 107, 1132, 109, 110, 1135, 4208, 5233, 5234, 4211, 5236, 4213, 118, 1143, 1144, 121, 122, 1147, 124, 1149, 1150, 127, 36992, 38017, 38018, 36995, 38020, 36997, 32902, 33927, 33928, 32905, 32906, 33931, 32908, 33933, 33934, 32911, 38032, 37009, 37010, 38035, 37012, 38037, 33942, 32919, 32920, 33945, 33946, 32923, 33948, 32925, 32926, 33951, 38048, 37025, 37026, 38051, 37028, 38053, 33958, 32935, 32936, 33961, 33962, 32939, 33964, 32941, 32942, 33967, 37040, 38065, 38066, 37043, 38068, 37045, 32950, 33975, 33976, 32953, 32954, 33979, 32956, 33981, 33982, 32959, 38080, 37057, 37058, 38083, 37060, 38085, 33990, 32967, 32968, 33993, 33994, 32971, 33996, 32973, 32974, 33999, 37072, 38097, 38098, 37075, 38100, 37077, 32982, 34007, 34008, 32985, 32986, 34011, 32988, 34013, 34014, 32991, 37088, 38113, 38114, 37091, 38116, 37093, 32998, 34023, 34024, 33001, 33002, 34027, 33004, 34029, 34030, 33007, 38128, 37105, 37106, 38131, 37108, 38133, 34038, 33015, 33016, 34041, 34042, 33019, 34044, 33021, 33022, 34047, 21760, 4353, 4354, 5379, 4356, 5381, 1286, 263, 264, 1289, 1290, 267, 1292, 269, 270, 1295, 4368, 5393, 5394, 4371, 5396, 4373, 278, 1303, 1304, 281, 282, 1307, 284, 1309, 1310, 287, 4384, 5409, 5410, 4387, 5412, 4389, 294, 1319, 1320, 297, 298, 1323, 300, 1325, 1326, 303, 5424, 4401, 4402, 5427, 4404, 5429, 1334, 311, 312, 1337, 1338, 315, 1340, 317, 318, 1343, 4416, 5441, 5442, 4419, 5444, 4421, 326, 1351, 1352, 329, 330, 1355, 332, 1357, 1358, 335, 5456, 4433, 4434, 5459, 4436, 5461, 1366, 343, 344, 1369, 1370, 347, 1372, 349, 350, 1375, 5472, 4449, 4450, 5475, 4452, 5477
    };
    
    public final static int[] AND_OR_XOR_TABLE = new int[] {
        84, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20,
        20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16,
        20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20,
        20, 16, 20, 16, 16, 20, 20, 16, 16, 20, 16, 20, 20, 16, 16, 20, 20, 16, 20, 16, 16, 20, 16, 20, 20, 16, 20, 16, 16, 20,
        20, 16, 16, 20, 16, 20, 20, 16, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148,
        148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148, 144,
        148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 144, 148,
        148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148, 148, 144, 144, 148, 144, 148, 148, 144,
        148, 144, 144, 148, 144, 148, 148, 144, 144, 148, 148, 144, 148, 144, 144, 148,};
    
    
    //////////////////// OUTDATED //////////////////////////////////////////
    
    /* parityTable[i] = (number of 1's in i is odd) ? 0 : 4, i = 0..255 */
    public final static short PARITY_TABLE[] = {
        4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4,
        0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0,
        0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0,
        4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4
    };

    // rrcaTable[i] = ((i & 1) << 7)|(i>>1);
    public final static short RRCA_TABLE[] = {
        0, 128, 1, 129, 2, 130, 3, 131, 4, 132, 5, 133, 6, 134, 7, 135, 8, 136, 9, 137, 10, 138, 11, 139, 12, 140, 13, 141, 14, 142, 15, 143, 16, 144, 17, 145, 18, 146, 19, 147, 20, 148, 21, 149, 22, 150,
        23, 151, 24, 152, 25, 153, 26, 154, 27, 155, 28, 156, 29, 157, 30, 158, 31, 159, 32, 160, 33, 161, 34, 162, 35, 163, 36, 164, 37, 165, 38, 166, 39, 167, 40, 168, 41, 169, 42, 170, 43, 171, 44, 172, 45,
        173, 46, 174, 47, 175, 48, 176, 49, 177, 50, 178, 51, 179, 52, 180, 53, 181, 54, 182, 55, 183, 56, 184, 57, 185, 58, 186, 59, 187, 60, 188, 61, 189, 62, 190, 63, 191, 64, 192, 65, 193, 66, 194, 67, 195,
        68, 196, 69, 197, 70, 198, 71, 199, 72, 200, 73, 201, 74, 202, 75, 203, 76, 204, 77, 205, 78, 206, 79, 207, 80, 208, 81, 209, 82, 210, 83, 211, 84, 212, 85, 213, 86, 214, 87, 215, 88, 216, 89, 217, 90,
        218, 91, 219, 92, 220, 93, 221, 94, 222, 95, 223, 96, 224, 97, 225, 98, 226, 99, 227, 100, 228, 101, 229, 102, 230, 103, 231, 104, 232, 105, 233, 106, 234, 107, 235, 108, 236, 109, 237, 110, 238, 111, 239, 112, 240,
        113, 241, 114, 242, 115, 243, 116, 244, 117, 245, 118, 246, 119, 247, 120, 248, 121, 249, 122, 250, 123, 251, 124, 252, 125, 253, 126, 254, 127, 255
    };
    
    // daaTable[i] = (i & 0x80)|((i==0) << 6)|parityTable[i]
    public final static short DAA_TABLE[] = {
        68, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4,
        0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 4, 0, 0, 4, 0, 4, 4, 0, 0, 4, 4, 0, 4, 0, 0, 4, 0, 4, 4, 0, 4, 0, 0, 4, 4,
        0, 0, 4, 0, 4, 4, 0, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132,
        132, 128, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132,
        128, 132, 132, 128, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 128, 132,
        132, 128, 132, 128, 128, 132, 132, 128, 128, 132, 128, 132, 132, 128, 132, 128, 128, 132, 128, 132, 132, 128, 128, 132, 132, 128, 132, 128, 128, 132,};
    
    // negTable[i] = (i&0x80)|((i==0)<<6)|2|((i==0x80)<<2)|(i!=0)|(((i&0x0f)!=0)<<4)
    public static final short NEG_TABLE[] = {
        66, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19,
        19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19,
        19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 3, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 135, 147, 147, 147, 147, 147, 147, 147,
        147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147,
        147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147,
        147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 131, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147, 147,};
    
}

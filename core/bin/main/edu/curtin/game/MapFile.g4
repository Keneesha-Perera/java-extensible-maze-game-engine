grammar MapFile;

options {
    language = Java;
}

@header {
package edu.curtin.game;
}

// Entry rule for parsing a game map file
file        : declaration* EOF ;

declaration : sizeDecl
            | startDecl
            | goalDecl
            | itemDecl
            | obstacleDecl
            | pluginDecl
            | scriptDecl
            ;

// Declarations for the size of the map,player's starting location, and goal location on the map
sizeDecl    : 'size' location ;
startDecl   : 'start' location ;
goalDecl    : 'goal' location ;

//Dcelares item/obstacle that appears at one or more locations
itemDecl    : 'item' STRING '{' 'at' locationList 'message' STRING '}' ;
obstacleDecl: 'obstacle' '{' 'at' locationList 'requires' stringList '}' ;

//Declares plugin/script to be loaded
pluginDecl  : 'plugin' qualifiedName ;
scriptDecl  : 'script' SCRIPTBLOCK ;

// Common parsing rules
location     : '(' INT ',' INT ')' ;
locationList : location (',' location)* ;
stringList   : STRING (',' STRING)* ;
qualifiedName: IDENT ('.' IDENT)* ;

// Lexer rules
STRING       : '"' (~["])* '"' ;

// Multiline SCRIPTBLOCK
SCRIPTBLOCK : '!{' ( . | '\r' | '\n' )*? '}' ;
IDENT        : [a-zA-Z_] [a-zA-Z0-9_]* ;
INT          : [0-9]+ ;

// Whitespace and comments are ignored
WS           : [ \t\r\n]+ -> skip ;

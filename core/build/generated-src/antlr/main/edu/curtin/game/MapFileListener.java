// Generated from edu/curtin/game/MapFile.g4 by ANTLR 4.13.1

package edu.curtin.game;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MapFileParser}.
 */
public interface MapFileListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MapFileParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(MapFileParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(MapFileParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(MapFileParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(MapFileParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#sizeDecl}.
	 * @param ctx the parse tree
	 */
	void enterSizeDecl(MapFileParser.SizeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#sizeDecl}.
	 * @param ctx the parse tree
	 */
	void exitSizeDecl(MapFileParser.SizeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#startDecl}.
	 * @param ctx the parse tree
	 */
	void enterStartDecl(MapFileParser.StartDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#startDecl}.
	 * @param ctx the parse tree
	 */
	void exitStartDecl(MapFileParser.StartDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#goalDecl}.
	 * @param ctx the parse tree
	 */
	void enterGoalDecl(MapFileParser.GoalDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#goalDecl}.
	 * @param ctx the parse tree
	 */
	void exitGoalDecl(MapFileParser.GoalDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#itemDecl}.
	 * @param ctx the parse tree
	 */
	void enterItemDecl(MapFileParser.ItemDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#itemDecl}.
	 * @param ctx the parse tree
	 */
	void exitItemDecl(MapFileParser.ItemDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#obstacleDecl}.
	 * @param ctx the parse tree
	 */
	void enterObstacleDecl(MapFileParser.ObstacleDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#obstacleDecl}.
	 * @param ctx the parse tree
	 */
	void exitObstacleDecl(MapFileParser.ObstacleDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#pluginDecl}.
	 * @param ctx the parse tree
	 */
	void enterPluginDecl(MapFileParser.PluginDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#pluginDecl}.
	 * @param ctx the parse tree
	 */
	void exitPluginDecl(MapFileParser.PluginDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#scriptDecl}.
	 * @param ctx the parse tree
	 */
	void enterScriptDecl(MapFileParser.ScriptDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#scriptDecl}.
	 * @param ctx the parse tree
	 */
	void exitScriptDecl(MapFileParser.ScriptDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(MapFileParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(MapFileParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#locationList}.
	 * @param ctx the parse tree
	 */
	void enterLocationList(MapFileParser.LocationListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#locationList}.
	 * @param ctx the parse tree
	 */
	void exitLocationList(MapFileParser.LocationListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#stringList}.
	 * @param ctx the parse tree
	 */
	void enterStringList(MapFileParser.StringListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#stringList}.
	 * @param ctx the parse tree
	 */
	void exitStringList(MapFileParser.StringListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MapFileParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void enterQualifiedName(MapFileParser.QualifiedNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MapFileParser#qualifiedName}.
	 * @param ctx the parse tree
	 */
	void exitQualifiedName(MapFileParser.QualifiedNameContext ctx);
}
// Generated from edu/curtin/game/MapFile.g4 by ANTLR 4.13.1

package edu.curtin.game;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MapFileParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, STRING=17, 
		SCRIPTBLOCK=18, IDENT=19, INT=20, WS=21;
	public static final int
		RULE_file = 0, RULE_declaration = 1, RULE_sizeDecl = 2, RULE_startDecl = 3, 
		RULE_goalDecl = 4, RULE_itemDecl = 5, RULE_obstacleDecl = 6, RULE_pluginDecl = 7, 
		RULE_scriptDecl = 8, RULE_location = 9, RULE_locationList = 10, RULE_stringList = 11, 
		RULE_qualifiedName = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "declaration", "sizeDecl", "startDecl", "goalDecl", "itemDecl", 
			"obstacleDecl", "pluginDecl", "scriptDecl", "location", "locationList", 
			"stringList", "qualifiedName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'size'", "'start'", "'goal'", "'item'", "'{'", "'at'", "'message'", 
			"'}'", "'obstacle'", "'requires'", "'plugin'", "'script'", "'('", "','", 
			"')'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, "STRING", "SCRIPTBLOCK", "IDENT", "INT", 
			"WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MapFile.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MapFileParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(MapFileParser.EOF, 0); }
		public List<DeclarationContext> declaration() {
			return getRuleContexts(DeclarationContext.class);
		}
		public DeclarationContext declaration(int i) {
			return getRuleContext(DeclarationContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 6686L) != 0)) {
				{
				{
				setState(26);
				declaration();
				}
				}
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(32);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DeclarationContext extends ParserRuleContext {
		public SizeDeclContext sizeDecl() {
			return getRuleContext(SizeDeclContext.class,0);
		}
		public StartDeclContext startDecl() {
			return getRuleContext(StartDeclContext.class,0);
		}
		public GoalDeclContext goalDecl() {
			return getRuleContext(GoalDeclContext.class,0);
		}
		public ItemDeclContext itemDecl() {
			return getRuleContext(ItemDeclContext.class,0);
		}
		public ObstacleDeclContext obstacleDecl() {
			return getRuleContext(ObstacleDeclContext.class,0);
		}
		public PluginDeclContext pluginDecl() {
			return getRuleContext(PluginDeclContext.class,0);
		}
		public ScriptDeclContext scriptDecl() {
			return getRuleContext(ScriptDeclContext.class,0);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_declaration);
		try {
			setState(41);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				sizeDecl();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				startDecl();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				goalDecl();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 4);
				{
				setState(37);
				itemDecl();
				}
				break;
			case T__8:
				enterOuterAlt(_localctx, 5);
				{
				setState(38);
				obstacleDecl();
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(39);
				pluginDecl();
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 7);
				{
				setState(40);
				scriptDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SizeDeclContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public SizeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sizeDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterSizeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitSizeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitSizeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SizeDeclContext sizeDecl() throws RecognitionException {
		SizeDeclContext _localctx = new SizeDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sizeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__0);
			setState(44);
			location();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartDeclContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public StartDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterStartDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitStartDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitStartDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartDeclContext startDecl() throws RecognitionException {
		StartDeclContext _localctx = new StartDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_startDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__1);
			setState(47);
			location();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GoalDeclContext extends ParserRuleContext {
		public LocationContext location() {
			return getRuleContext(LocationContext.class,0);
		}
		public GoalDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_goalDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterGoalDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitGoalDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitGoalDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GoalDeclContext goalDecl() throws RecognitionException {
		GoalDeclContext _localctx = new GoalDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_goalDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			match(T__2);
			setState(50);
			location();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ItemDeclContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(MapFileParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MapFileParser.STRING, i);
		}
		public LocationListContext locationList() {
			return getRuleContext(LocationListContext.class,0);
		}
		public ItemDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itemDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterItemDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitItemDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitItemDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ItemDeclContext itemDecl() throws RecognitionException {
		ItemDeclContext _localctx = new ItemDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_itemDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(T__3);
			setState(53);
			match(STRING);
			setState(54);
			match(T__4);
			setState(55);
			match(T__5);
			setState(56);
			locationList();
			setState(57);
			match(T__6);
			setState(58);
			match(STRING);
			setState(59);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ObstacleDeclContext extends ParserRuleContext {
		public LocationListContext locationList() {
			return getRuleContext(LocationListContext.class,0);
		}
		public StringListContext stringList() {
			return getRuleContext(StringListContext.class,0);
		}
		public ObstacleDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_obstacleDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterObstacleDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitObstacleDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitObstacleDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ObstacleDeclContext obstacleDecl() throws RecognitionException {
		ObstacleDeclContext _localctx = new ObstacleDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_obstacleDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(T__8);
			setState(62);
			match(T__4);
			setState(63);
			match(T__5);
			setState(64);
			locationList();
			setState(65);
			match(T__9);
			setState(66);
			stringList();
			setState(67);
			match(T__7);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PluginDeclContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public PluginDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pluginDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterPluginDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitPluginDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitPluginDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PluginDeclContext pluginDecl() throws RecognitionException {
		PluginDeclContext _localctx = new PluginDeclContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_pluginDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(T__10);
			setState(70);
			qualifiedName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ScriptDeclContext extends ParserRuleContext {
		public TerminalNode SCRIPTBLOCK() { return getToken(MapFileParser.SCRIPTBLOCK, 0); }
		public ScriptDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scriptDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterScriptDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitScriptDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitScriptDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScriptDeclContext scriptDecl() throws RecognitionException {
		ScriptDeclContext _localctx = new ScriptDeclContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_scriptDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__11);
			setState(73);
			match(SCRIPTBLOCK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(MapFileParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(MapFileParser.INT, i);
		}
		public LocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_location; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterLocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitLocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitLocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationContext location() throws RecognitionException {
		LocationContext _localctx = new LocationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_location);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(T__12);
			setState(76);
			match(INT);
			setState(77);
			match(T__13);
			setState(78);
			match(INT);
			setState(79);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocationListContext extends ParserRuleContext {
		public List<LocationContext> location() {
			return getRuleContexts(LocationContext.class);
		}
		public LocationContext location(int i) {
			return getRuleContext(LocationContext.class,i);
		}
		public LocationListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_locationList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterLocationList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitLocationList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitLocationList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocationListContext locationList() throws RecognitionException {
		LocationListContext _localctx = new LocationListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_locationList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			location();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(82);
				match(T__13);
				setState(83);
				location();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringListContext extends ParserRuleContext {
		public List<TerminalNode> STRING() { return getTokens(MapFileParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(MapFileParser.STRING, i);
		}
		public StringListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterStringList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitStringList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitStringList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringListContext stringList() throws RecognitionException {
		StringListContext _localctx = new StringListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(STRING);
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13) {
				{
				{
				setState(90);
				match(T__13);
				setState(91);
				match(STRING);
				}
				}
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualifiedNameContext extends ParserRuleContext {
		public List<TerminalNode> IDENT() { return getTokens(MapFileParser.IDENT); }
		public TerminalNode IDENT(int i) {
			return getToken(MapFileParser.IDENT, i);
		}
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MapFileListener ) ((MapFileListener)listener).exitQualifiedName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MapFileVisitor ) return ((MapFileVisitor<? extends T>)visitor).visitQualifiedName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_qualifiedName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(IDENT);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__15) {
				{
				{
				setState(98);
				match(T__15);
				setState(99);
				match(IDENT);
				}
				}
				setState(104);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0015j\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0005\u0000\u001c\b\u0000\n\u0000\f\u0000\u001f"+
		"\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001*\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0005\nU\b\n\n\n\f\nX\t\n\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0005\u000b]\b\u000b\n\u000b\f\u000b`\t\u000b\u0001\f\u0001\f\u0001\f"+
		"\u0005\fe\b\f\n\f\f\fh\t\f\u0001\f\u0000\u0000\r\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0000f\u0000\u001d\u0001"+
		"\u0000\u0000\u0000\u0002)\u0001\u0000\u0000\u0000\u0004+\u0001\u0000\u0000"+
		"\u0000\u0006.\u0001\u0000\u0000\u0000\b1\u0001\u0000\u0000\u0000\n4\u0001"+
		"\u0000\u0000\u0000\f=\u0001\u0000\u0000\u0000\u000eE\u0001\u0000\u0000"+
		"\u0000\u0010H\u0001\u0000\u0000\u0000\u0012K\u0001\u0000\u0000\u0000\u0014"+
		"Q\u0001\u0000\u0000\u0000\u0016Y\u0001\u0000\u0000\u0000\u0018a\u0001"+
		"\u0000\u0000\u0000\u001a\u001c\u0003\u0002\u0001\u0000\u001b\u001a\u0001"+
		"\u0000\u0000\u0000\u001c\u001f\u0001\u0000\u0000\u0000\u001d\u001b\u0001"+
		"\u0000\u0000\u0000\u001d\u001e\u0001\u0000\u0000\u0000\u001e \u0001\u0000"+
		"\u0000\u0000\u001f\u001d\u0001\u0000\u0000\u0000 !\u0005\u0000\u0000\u0001"+
		"!\u0001\u0001\u0000\u0000\u0000\"*\u0003\u0004\u0002\u0000#*\u0003\u0006"+
		"\u0003\u0000$*\u0003\b\u0004\u0000%*\u0003\n\u0005\u0000&*\u0003\f\u0006"+
		"\u0000\'*\u0003\u000e\u0007\u0000(*\u0003\u0010\b\u0000)\"\u0001\u0000"+
		"\u0000\u0000)#\u0001\u0000\u0000\u0000)$\u0001\u0000\u0000\u0000)%\u0001"+
		"\u0000\u0000\u0000)&\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000"+
		")(\u0001\u0000\u0000\u0000*\u0003\u0001\u0000\u0000\u0000+,\u0005\u0001"+
		"\u0000\u0000,-\u0003\u0012\t\u0000-\u0005\u0001\u0000\u0000\u0000./\u0005"+
		"\u0002\u0000\u0000/0\u0003\u0012\t\u00000\u0007\u0001\u0000\u0000\u0000"+
		"12\u0005\u0003\u0000\u000023\u0003\u0012\t\u00003\t\u0001\u0000\u0000"+
		"\u000045\u0005\u0004\u0000\u000056\u0005\u0011\u0000\u000067\u0005\u0005"+
		"\u0000\u000078\u0005\u0006\u0000\u000089\u0003\u0014\n\u00009:\u0005\u0007"+
		"\u0000\u0000:;\u0005\u0011\u0000\u0000;<\u0005\b\u0000\u0000<\u000b\u0001"+
		"\u0000\u0000\u0000=>\u0005\t\u0000\u0000>?\u0005\u0005\u0000\u0000?@\u0005"+
		"\u0006\u0000\u0000@A\u0003\u0014\n\u0000AB\u0005\n\u0000\u0000BC\u0003"+
		"\u0016\u000b\u0000CD\u0005\b\u0000\u0000D\r\u0001\u0000\u0000\u0000EF"+
		"\u0005\u000b\u0000\u0000FG\u0003\u0018\f\u0000G\u000f\u0001\u0000\u0000"+
		"\u0000HI\u0005\f\u0000\u0000IJ\u0005\u0012\u0000\u0000J\u0011\u0001\u0000"+
		"\u0000\u0000KL\u0005\r\u0000\u0000LM\u0005\u0014\u0000\u0000MN\u0005\u000e"+
		"\u0000\u0000NO\u0005\u0014\u0000\u0000OP\u0005\u000f\u0000\u0000P\u0013"+
		"\u0001\u0000\u0000\u0000QV\u0003\u0012\t\u0000RS\u0005\u000e\u0000\u0000"+
		"SU\u0003\u0012\t\u0000TR\u0001\u0000\u0000\u0000UX\u0001\u0000\u0000\u0000"+
		"VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000W\u0015\u0001\u0000"+
		"\u0000\u0000XV\u0001\u0000\u0000\u0000Y^\u0005\u0011\u0000\u0000Z[\u0005"+
		"\u000e\u0000\u0000[]\u0005\u0011\u0000\u0000\\Z\u0001\u0000\u0000\u0000"+
		"]`\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000"+
		"\u0000_\u0017\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000af\u0005"+
		"\u0013\u0000\u0000bc\u0005\u0010\u0000\u0000ce\u0005\u0013\u0000\u0000"+
		"db\u0001\u0000\u0000\u0000eh\u0001\u0000\u0000\u0000fd\u0001\u0000\u0000"+
		"\u0000fg\u0001\u0000\u0000\u0000g\u0019\u0001\u0000\u0000\u0000hf\u0001"+
		"\u0000\u0000\u0000\u0005\u001d)V^f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
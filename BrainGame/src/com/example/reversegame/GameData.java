package com.example.reversegame;

/**
 * 数据类，用于记录各种游戏模式和分数
 * 
 * @author chaolin
 * 
 */
public class GameData {
	/* 经典模式 */
	public static final int CLASSISCMODEL = 0;
	/* 挑战模式 */
	public static final int CHALLENGEMODEL = 1;
	/* 闯关模式 */
	public static final int BREAKTHROUGHMODEL = 2;
	/* 经典模式最高分 */
	public static int maxClassisc = 0;
	/* 挑战模式最高分 */
	public static int maxChallenge = 0;
	/* 闯关模式最高分 */
	public static int maxBreakthrough = 0;
	/* 当前分数 */
	public static int currentGrade = 0;
	/* 当前游戏模式 */
	public static int currentGameModel = GameData.CLASSISCMODEL;
	/* 是否播放音效 */
	public static boolean isPlaySounds=false;
}

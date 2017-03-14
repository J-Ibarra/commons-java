package br.com.crypto.commons.score.helper;

import org.apache.commons.lang3.StringUtils;

public enum ScoreConditions {

	STRING, NUMERICSTRING, ALPHANUMERICSTRING, NOTEMPTY, NOWHITESPACE, UPPERCASE, LOWERCASE, NUMERIC, NOTNULL, NOTZERO;
	
	public static float POSITIVESCORE = 0.1F;
	public static float NEGATIVESCORE = -0.1F;

	public static ScoreConditions[] getScoreConditionsArray(ScoreConditions... conditions) {
		return conditions;
	}
	
	public static final float hasCondition(String str, ScoreConditions condition){
		
		float score = 0F;
		
		switch (condition) {
		case NUMERICSTRING:
			score += StringUtils.isNumeric(str) ? POSITIVESCORE : NEGATIVESCORE;
			break;
		case NOTEMPTY:
			score += StringUtils.isNotEmpty(str.trim()) ? POSITIVESCORE : NEGATIVESCORE;
			break;
		case NOWHITESPACE:
			score += !StringUtils.containsWhitespace(str.trim()) ? POSITIVESCORE : NEGATIVESCORE;
			break;
		case LOWERCASE:
			score += StringUtils.isAllLowerCase(str) ? POSITIVESCORE : NEGATIVESCORE;
			break;
		case UPPERCASE:
			score += StringUtils.isAllUpperCase(str) ? POSITIVESCORE : NEGATIVESCORE;
			break;
		case NOTNULL:
			score += str != null ? POSITIVESCORE : NEGATIVESCORE;
			break;
		}
		
		return score;
		
	}
	
	public static final float hasCondition(Number number, ScoreConditions condition){
		
		float score = 0F;
		
		switch (condition) {
		case NOTZERO:
			score += number.floatValue() > 0 ? POSITIVESCORE : NEGATIVESCORE;
			break;
		case NOTNULL:
			score += number != null ? POSITIVESCORE : NEGATIVESCORE;
			break;
		}
		
		return score;
		
	}

}

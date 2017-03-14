package br.com.crypto.commons.score;

import java.util.List;
import java.util.Map;

import br.com.crypto.commons.score.helper.ScoreConditions;

public interface Score {

	List<Object[]> getScoreByField(List<Object[]> objectArrayList, Map<Integer, ScoreConditions[]> mapFieldsValidation);
	List<Object[]> getScoreByListCompare(List<Object[]> objectArrayList, List<Object[]> objectArrayListFilter);
	float getScoreByConditions(Object object, ScoreConditions[] scoreConditionArray);
	
}

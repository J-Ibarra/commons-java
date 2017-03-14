package br.com.crypto.commons.score.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;

import br.com.crypto.commons.score.Score;
import br.com.crypto.commons.score.helper.ScoreConditions;

public class ScoreImpl implements Score {

	@Override
	public List<Object[]> getScoreByField(List<Object[]> objectArrayList, Map<Integer, ScoreConditions[]> mapFieldsValidation) {

		Set<Integer> keySet = mapFieldsValidation.keySet();

		List<Object[]> newList = new ArrayList<>();

		for (Object[] objects : objectArrayList) {

			Object[] newObjectArray = new Object[objects.length + 1];

			System.arraycopy(objects, 0, newObjectArray, 0, objects.length);

			newObjectArray[objects.length] = 0f;

			for (Integer key : keySet) {

				newObjectArray[newObjectArray.length - 1] = ((Float) newObjectArray[newObjectArray.length - 1])
						+ getScoreByConditions(newObjectArray[key], mapFieldsValidation.get(key));

			}

			newList.add(newObjectArray);
		}

		Collections.sort(newList, new Comparator<Object[]>() {

			@Override
			public int compare(Object[] o1, Object[] o2) {
				if ((Float) o1[o1.length - 1] >= (Float) o2[o2.length - 1])
					return 1;
				return 0;
			}
		});

		return newList;
	}
	
	@Override
	public List<Object[]> getScoreByListCompare(List<Object[]> objectArrayList, List<Object[]> objectArrayListFilter) {
		//TODO
		return null;
	}

	public float getScoreByConditions(Object object, ScoreConditions[] scoreConditionArray) {

		float score = 0f;

		if (object instanceof String && ArrayUtils.contains(scoreConditionArray, ScoreConditions.STRING)) {

			score += ScoreConditions.POSITIVESCORE;

			String stringValueOf = (String) object;

			for (ScoreConditions condition : scoreConditionArray) {

				score += ScoreConditions.hasCondition(stringValueOf, condition);

			}

		} else if (ArrayUtils.contains(scoreConditionArray, ScoreConditions.STRING)) {
			score += ScoreConditions.NEGATIVESCORE;
		}

		if (object instanceof Number && ArrayUtils.contains(scoreConditionArray, ScoreConditions.NUMERIC)) {

			score += ScoreConditions.POSITIVESCORE;

			for (ScoreConditions condition : scoreConditionArray) {

				score += ScoreConditions.hasCondition((Number)object, condition);

			}

		} else if (ArrayUtils.contains(scoreConditionArray, ScoreConditions.NUMERIC)) {
			score += ScoreConditions.NEGATIVESCORE;
		}

		return score;
	}

}

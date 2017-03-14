package br.com.crypto.commons.date.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.crypto.commons.date.Holiday;

public class HolidayImpl implements Holiday {

	@Override
	public Boolean isHoliday(Calendar data) {

		Calendar carnaval = getCarnaval(null);
		Calendar segundaCarnaval = getCarnavalSegundaFeira(null);
		Calendar corpusChristi = getCorpusChristi(null);
		Calendar sextaPaixao = getSextaFeiraPaixao(null);

		Calendar anoNovo = Calendar.getInstance();
		anoNovo.set(Calendar.DAY_OF_MONTH, 1);
		anoNovo.set(Calendar.MONTH, 1);

		Calendar tiradentes = Calendar.getInstance();
		tiradentes.set(Calendar.DAY_OF_MONTH, 21);
		tiradentes.set(Calendar.MONTH, 4);

		Calendar trabalho = Calendar.getInstance();
		trabalho.set(Calendar.DAY_OF_MONTH, 1);
		trabalho.set(Calendar.MONTH, 5);

		Calendar revolucao = Calendar.getInstance();
		revolucao.set(Calendar.DAY_OF_MONTH, 9);
		revolucao.set(Calendar.MONTH, 7);

		Calendar independecia = Calendar.getInstance();
		independecia.set(Calendar.DAY_OF_MONTH, 7);
		independecia.set(Calendar.MONTH, 9);

		Calendar nossaSenhora = Calendar.getInstance();
		nossaSenhora.set(Calendar.DAY_OF_MONTH, 12);
		nossaSenhora.set(Calendar.MONTH, 10);

		Calendar finados = Calendar.getInstance();
		finados.set(Calendar.DAY_OF_MONTH, 02);
		finados.set(Calendar.MONTH, 11);

		Calendar proclamacao = Calendar.getInstance();
		proclamacao.set(Calendar.DAY_OF_MONTH, 15);
		proclamacao.set(Calendar.MONTH, 11);

		Calendar justica = Calendar.getInstance();
		justica.set(Calendar.DAY_OF_MONTH, 8);
		justica.set(Calendar.MONTH, 12);

		Calendar natal = Calendar.getInstance();
		natal.set(Calendar.DAY_OF_MONTH, 24);
		natal.set(Calendar.MONTH, 12);

		List<Calendar> holiday = new ArrayList<>();

		holiday.add(anoNovo);
		holiday.add(segundaCarnaval);
		holiday.add(carnaval);
		holiday.add(tiradentes);
		holiday.add(sextaPaixao);
		holiday.add(trabalho);
		holiday.add(corpusChristi);
		holiday.add(revolucao);
		holiday.add(independecia);
		holiday.add(nossaSenhora);
		holiday.add(finados);
		holiday.add(proclamacao);
		holiday.add(justica);
		holiday.add(natal);

		if (holiday.contains(data)) {
			return true;
		}

		return false;
	}

	@Override
	public Calendar getCarnaval(Integer year) {

		Calendar pascoa = getPascoa(year);
		Long carnavalLong = (pascoa.getTimeInMillis() - (47 * 86400));
		Calendar carnaval = Calendar.getInstance();
		carnaval.setTimeInMillis(carnavalLong);

		return carnaval;

	}

	@Override
	public Calendar getCarnavalSegundaFeira(Integer year) {

		Calendar pascoa = getPascoa(year);
		Long carnavalLong = (pascoa.getTimeInMillis() - (48 * 86400));
		Calendar carnaval = Calendar.getInstance();
		carnaval.setTimeInMillis(carnavalLong);

		return carnaval;

	}

	@Override
	public Calendar getCorpusChristi(Integer year) {

		Calendar pascoa = getPascoa(year);
		Long carnavalLong = (pascoa.getTimeInMillis() + (60 * 86400));
		Calendar corpusChristi = Calendar.getInstance();
		corpusChristi.setTimeInMillis(carnavalLong);

		return corpusChristi;

	}

	@Override
	public Calendar getSextaFeiraPaixao(Integer year) {

		Calendar pascoa = getPascoa(year);
		Long carnavalLong = (pascoa.getTimeInMillis() - (2 * 86400));
		Calendar sextaPaixao = Calendar.getInstance();
		sextaPaixao.setTimeInMillis(carnavalLong);

		return sextaPaixao;

	}

	@Override
	public Calendar getPascoa(Integer year) {

		Integer day;
		Integer month;
		if (year == null) {
			year = Calendar.getInstance().get(Calendar.YEAR);
		}

		Integer x = 24;
		Integer y = 5;
		Integer a = year % 19;
		Integer b = year % 4;
		Integer c = year % 7;
		Integer d = (19 * a + x) % 30;
		Integer e = (2 * b + 4 * c + 6 * d + y) % 7;

		if ((d + e) > 9) {
			day = (d + e) - 9;
			month = 4;
		} else {
			day = d + e + 22;
			month = 3;
		}

		if (month == 4) {

			if (day == 26) {
				day = (day - 7);
			}

			if (day == 25 && d == 28 && a > 10) {
				day = 18;
			}
		}

		Calendar pascoa = Calendar.getInstance();
		pascoa.set(year, month, day);

		return pascoa;

	}

}

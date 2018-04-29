package com.sistema.easyservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.stereotype.Component;

/**
 * Utilitario para facilitar o uso do  tipo Date
 * @author Andre Felippe
 * 
 */
@Component
public class DataUtil {

	public static int diaDaSemana(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	public static int dia(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int mes(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.MONTH);
	}
	
	public static int ano(Date data) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		return cal.get(Calendar.YEAR);
	}

	public static Date adicionarDias(Date data,int quantidadeDeDias){
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DATE, quantidadeDeDias);
		return cal.getTime();
	}
	
	public static Date dataToString(String data, String pattern) throws Exception { 
		if (data == null || data.equals(""))
			return null;
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}
	
	public static Date getDate(int dia, int mes, int ano){
		return new GregorianCalendar(ano, mes + 1, dia).getTime();
	}
	
	public static String dataFormatada(String pattern,Date date){
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String stringMes(int mes){
		
		switch (mes) {
		case 0:
			return "Janeiro";
		case 1:
			return "Fevereiro";
		case 2:
			return "Março";
		case 3:
			return "Abril";
		case 4:
			return "Maio";
		case 5:
			return "Junho";
		case 6:
			return "Julho";
		case 7:
			return "Agosto";
		case 8:
			return "Setembro";
		case 9:
			return "Outubro";
		case 10:
			return "Novembro";
		case 11:
			return "Dezembro";	
		default:
			return null;
		}
	}
	
	public static String stringDiaDaSemana(int dia){
		
		switch (dia) {
		case 2:
			return "SEG";
		case 3:
			return "TER";
		case 4:
			return "QUA";
		case 5:
			return "QUI";
		case 6:
			return "SEX";
		default:
			return null;
		}
	}
	
}

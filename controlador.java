package com.example.digitales.controller;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.digitales.DigitalesApplication;

//@RestController
@Controller
public class controlador {
	private static int HC = 0;
	private static int LPD = 0;
	private static int RPD = 0;
	private static int HC_R = 0;
	private static int LPD_R = 0;
	private static int RPD_R = 0;
	private static int TOTAL=0;
	private static int ACIERTOS =0;
	private static int ERRORES=0;
	private static float POR_ACIERTOS=0;
	private static float POR_ERRORES=0;
	DigitalesApplication clase = new DigitalesApplication();
	private ArrayList<ArrayList<String>> LISTA = clase.getLISTA(); //DATOS OBTENIDOS DE LA CLASIFICACION [Real/Predicho]
	@RequestMapping("/{id}")
	@GetMapping
	public String prueba(@PathVariable int id) {
		 //imprime();
		 return LISTA.get(id).get(0);
	 }
	 
	 public void imprimePrediccion() {
		 for (int i=0;i<LISTA.size();i++ ) {
			 if(LISTA.get(i).get(0).equals("100")) {
				 HC=HC+1;
			 }else if(LISTA.get(i).get(0).equals("10")){
				 LPD=LPD+1;
			 }else {
				 RPD=RPD+1;
			 }
			 //System.out.println(HC+"-"+LPD+","+RPD);
		 } 
	 }
	 public void porcentajes() {
		 TOTAL=0;ACIERTOS=0;ERRORES=0;
		 //System.out.println(LISTA.get(0).get(0));
		 //System.out.println(LISTA.get(1).get(0));
		 for (int i=0;i<LISTA.size();i++ ){
			 if (LISTA.get(i).get(0).equals(LISTA.get(i).get(1))) {
				 ACIERTOS=ACIERTOS+1;
			 }else {
				 ERRORES=ERRORES+1;
			 }
		 }
		 //System.out.println(TOTAL);
		 TOTAL=ACIERTOS+ERRORES;
		 /*
		 System.out.println(TOTAL);
		 System.out.println(ACIERTOS);
		 System.out.println(ERRORES);
		 */
		 POR_ACIERTOS=((float)ACIERTOS/(float)TOTAL)*100;
		 //System.out.println((float)ACIERTOS/(float)TOTAL);
		 POR_ERRORES=((float)ERRORES/(float)TOTAL)*100;
	 }
	 
	 public void imprimeReal() {
		 for (int i=0;i<LISTA.size();i++ ) {
			 if(LISTA.get(i).get(1).equals("100")) {
				 HC_R=HC_R+1;
			 }else if(LISTA.get(i).get(1).equals("10")){
				 LPD_R=LPD_R+1;
			 }else {
				 RPD_R=RPD_R+1;
			 }
			 //System.out.println(HC_R+"-"+LPD_R+","+RPD_R);
		 }
		 
	 }
	@GetMapping("/resultados")
	public String barGraph(Model model) {
			imprimePrediccion();
			imprimeReal();
			Map<String, Integer> surveyMap = new LinkedHashMap<>();
			surveyMap.put("Healthy Controls", HC);
			surveyMap.put("Left Parkinson Disease", LPD);
			surveyMap.put("Rigth Parkinson Disease", RPD);
			model.addAttribute("surveyMap", surveyMap);
			Map<String, Integer> surveyRealMap = new LinkedHashMap<>();
			surveyRealMap.put("Healthy Controls", HC_R);
			surveyRealMap.put("Left Parkinson Disease", LPD_R);
			surveyRealMap.put("Rigth Parkinson Disease", RPD_R);
			model.addAttribute("surveyRealMap", surveyRealMap);
			HC=0;LPD=0;RPD=0;
			HC_R=0;LPD_R=0;RPD_R=0;
			return "barGraph";
	}

	@GetMapping("/historial")
	public String pieChart(Model model) {
		porcentajes();
		model.addAttribute("pass", POR_ACIERTOS);
		model.addAttribute("fail", POR_ERRORES);
		return "pieChart";
	}
	
}

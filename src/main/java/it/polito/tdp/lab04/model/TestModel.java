package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		// Punto 1
				StringBuilder sb = new StringBuilder();
				for (Corso corso : model.getTuttiICorsi()) {
					sb.append(String.format("%-8s ", corso.getCodins()));
					sb.append(String.format("%-2s ", corso.getCrediti()));
					sb.append(String.format("%-50s ", corso.getNome()));
					sb.append(String.format("%-4s ", corso.getPd()));
					sb.append("\n");
				}
				System.out.println(sb.toString());

				// Punto 2
				Studente studente = model.getStudente(146101);
				System.out.println(studente + "\n");

				// Punto 3
				sb = new StringBuilder();
				for (Studente s : model.studentiIscrittiAlCorso(new Corso("02AQJPG"))) {
					sb.append(String.format("%-10s ", s.getMatricola()));
					sb.append(String.format("%-20s ", s.getCognome()));
					sb.append(String.format("%-20s ", s.getNome()));
					sb.append(String.format("%-10s ", s.getCds()));
					sb.append("\n");
				}
				System.out.println(sb.toString());

				// Punto 4
				sb = new StringBuilder();
				for (Corso corso : model.corsiFromStudente(new Studente(146101))) {
					sb.append(String.format("%-8s ", corso.getCodins()));
					sb.append(String.format("%-2s ", corso.getCrediti()));
					sb.append(String.format("%-50s ", corso.getNome()));
					sb.append(String.format("%-4s ", corso.getPd()));
					sb.append("\n");
				}
				System.out.println(sb.toString());

				// Punto 5
				boolean result = model.isStudenteIscrittoACorso(new Studente(146101), new Corso("01NBAPG"));
				System.out.println(result);
				
				// Punto 6
				model.inscriviStudenteACorso(new Studente(146101), new Corso("01NBAPG"));
				
				// Ristampo punto 4
				sb = new StringBuilder();
				for (Corso corso : model.corsiFromStudente(new Studente(146101))) {
					sb.append(String.format("%-8s ", corso.getCodins()));
					sb.append(String.format("%-2s ", corso.getCrediti()));
					sb.append(String.format("%-50s ", corso.getNome()));
					sb.append(String.format("%-4s ", corso.getPd()));
					sb.append("\n");
				}
				System.out.println(sb.toString());

	}

}

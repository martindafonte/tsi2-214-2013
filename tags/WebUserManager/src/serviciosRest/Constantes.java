package serviciosRest;

public abstract class Constantes {
	public static final String apikey = "AIzaSyDiqVN49HuXEapUU39_Pz034XhzXWsjruY";
	
	public static final int Cte_Exito = 0;
	public static final int Cte_Error_No_Existe_App = 10;
	
	//Para servicios push se toma el rango desde 100-199
	public static final int Push_Excepcion = 100;
	public static final int Push_Error_Enviar = 101;
	public static final int Push_Error_No_Existe_Canal = 101;
	
	// para servicio Json 200-299
	public static final int Cte_Error_Buscar_Id = 200;
}

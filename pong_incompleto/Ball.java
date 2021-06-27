import java.awt.*;

/**
	Esta classe representa a bola usada no jogo. A classe princial do jogo (Pong)
	instancia um objeto deste tipo quando a execução é iniciada.
*/

public class Ball {

	/**
		Construtor da classe Ball. Observe que quem invoca o construtor desta classe define a velocidade da bola 
		(em pixels por millisegundo), mas não define a direção deste movimento. A direção do movimento é determinada 
		aleatóriamente pelo construtor.

		@param cx coordenada x da posição inicial da bola (centro do retangulo que a representa).
		@param cy coordenada y da posição inicial da bola (centro do retangulo que a representa).
		@param width largura do retangulo que representa a bola.
		@param height altura do retangulo que representa a bola.
		@param color cor da bola.
		@param speed velocidade da bola (em pixels por millisegundo).
	*/
	private double cx;
	private double cy;
	private double width;
	private double height;
	private Color color;
	private double speed;

	private double xDirection;
	private double yDirection;

	public Ball(double cx, double cy, double width, double height, Color color, double speed){
		this.cx = cx;
		this.cy = cy;
		this.width = width;
		this.height = height;
		this.color = color;
		this.speed = speed;

		this.xDirection = 1;
		this.yDirection = 1;
	}


	/**
		Método chamado sempre que a bola precisa ser (re)desenhada.
	*/

	public void draw(){

		GameLib.setColor(Color.YELLOW);
		GameLib.fillRect(cx, cy, 20, 20);
	}

	/**
		Método chamado quando o estado (posição) da bola precisa ser atualizado.
		
		@param delta quantidade de millisegundos que se passou entre o ciclo anterior de atualização do jogo e o atual.
	*/

	public void update(long delta){
		cx += speed * xDirection;
		cy += speed * yDirection;
		draw();
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com um jogador.
	
		@param playerId uma string cujo conteúdo identifica um dos jogadores.
	*/

	public void onPlayerCollision(String playerId){
		//problema: falta efeito quando bola bate no canto superior/inferior do jogador
		xDirection *= -1;
	}

	/**
		Método chamado quando detecta-se uma colisão da bola com uma parede.

		@param wallId uma string cujo conteúdo identifica uma das paredes da quadra.
	*/

	public void onWallCollision(String wallId){
		if("Bottom".equals(wallId) || "Top".equals(wallId)){
			yDirection *= -1;
		}else{
			xDirection *= -1;
		}
	}

	/**
		Método que verifica se houve colisão da bola com uma parede.

		@param wall referência para uma instância de Wall contra a qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/
	
	public boolean checkCollision(Wall wall){
		double xDistance = Math.abs(cx - wall.getCx());
		double xMinDistance = (width/2) + (wall.getWidth()/2);

		double yDistance = Math.abs(cy - wall.getCy());
		double yMinDistance = (height/2) + (wall.getHeight()/2);

		if("Bottom".equals(wall.getId()) || "Top".equals(wall.getId())){
			if(yDistance <= yMinDistance){
				return true;
			}			
		}else{
			if(xDistance <= xMinDistance){
				return true;
			}
		}

		return false;
	}


	/**
		Método que verifica se houve colisão da bola com um jogador.

		@param player referência para uma instância de Player contra o qual será verificada a ocorrência de colisão da bola.
		@return um valor booleano que indica a ocorrência (true) ou não (false) de colisão.
	*/	

	public boolean checkCollision(Player player){
		double xDistance = Math.abs(cx - player.getCx());
		double xMinDistance = (width/2) + (player.getWidth()/2);

		double yDistance = Math.abs(cy - player.getCy());
		double yMinDistance = (height/2) + (player.getHeight()/2);

		if(xDistance <= xMinDistance && yDistance <= yMinDistance){
			return true;
		}
	
		return false;
	}

	/**
		Método que devolve a coordenada x do centro do retângulo que representa a bola.
		@return o valor double da coordenada x.
	*/
	
	public double getCx(){

		return cx;
	}

	/**
		Método que devolve a coordenada y do centro do retângulo que representa a bola.
		@return o valor double da coordenada y.
	*/

	public double getCy(){

		return cy;
	}

	/**
		Método que devolve a velocidade da bola.
		@return o valor double da velocidade.

	*/

	public double getSpeed(){

		return speed;
	}

}

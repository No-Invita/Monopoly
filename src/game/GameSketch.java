package game;

import inputs.Console;
import inputs.Keyboard;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import game.player.Player;
import java.io.IOException;
import game.player.PlayerList;
import game.dices.dices;

public class GameSketch extends PApplet {
	int scene = 1, numPlayers = 1, index = 1;

	String[] input = new String[5];
	PImage[] dados = new PImage[6];
	PImage bg, sceneStart, sceneChoosePLayers, sceneInputNames, ofert, player;

	PImage piece1, piece2, piece3, piece4, dialog, ark, turn1D, turn2D, turn3D, turn4D,
			ofertexitjail, turn1L, turn2L, turn3L, turn4L, showownerships, luck;

	boolean changeturn = false, loadplayers = false, move = false, launch = true, sleep = false,
			ownerships = false;

	Console console = new Console(360, 386, 24, this);
	Keyboard teclado = new Keyboard(this, console);
	PlayerList lista;
	Bank bank;
	Player current;
	PFont font;;

	public GameSketch(Bank banco) {
		this.bank = banco;
		this.lista = banco.playerList;
	}

	@Override
	public void settings() {
		fullScreen();
	}

	@Override
	public void setup() {

		surface.setResizable(true);
		surface.setSize(960, 768);
		console.deactivate();
		font = createFont("src/font/monopoly-inline.ttf", 35);
		bg = loadImage("src/images/bg.png");
		sceneStart = loadImage("src/images/scene_start.png");
		sceneInputNames = loadImage("src/images/scene _inputnames.png");
		sceneChoosePLayers = loadImage("src/images/choosePlayers.png");
		ofertexitjail = loadImage("src/images/ofertexitjail.png");
		ofert = loadImage("src/images/ofert.png");
		piece1 = loadImage("src/images/piece1.png");
		piece2 = loadImage("src/images/piece2.png");
		piece3 = loadImage("src/images/piece3.png");
		piece4 = loadImage("src/images/piece4.png");
		dialog = loadImage("src/images/dialog.png");
		player = loadImage("src/images/player.png");
		luck = loadImage("src/images/luck.png");
		ark = loadImage("src/images/ark.png");
		turn1D = loadImage("src/images/turn1Dark.png");
		turn2D = loadImage("src/images/turn2Dark.png");
		turn3D = loadImage("src/images/turn3Dark.png");
		turn4D = loadImage("src/images/turn4Dark.png");
		turn1L = loadImage("src/images/turn1Light.png");
		turn2L = loadImage("src/images/turn2Light.png");
		turn3L = loadImage("src/images/turn3Light.png");
		turn4L = loadImage("src/images/turn4Light.png");
		showownerships = loadImage("src/images/showownerships.png");
		dices.upLoad();
		for (int i = 0; i < dices.dices.length; i++) {
			dados[i] = loadImage(dices.dices[i]);
		}
	}

	@Override
	public void draw() {

		switch (scene) {
			case 1: {
				image(sceneStart, 0, 0);
				fill(0);
				textFont(font);
				textSize(35);
				text("Presiona espacio para empezar", 277, 729);
				break;
			}
			case 2: {
				image(sceneChoosePLayers, 0, 0);
				textFont(font);
				fill(255, 255, 255);
				textSize(30);
				text("2 Jugadores", 98, 138);
				text("3 Jugadores", 98, 378);
				text("4 Jugadores", 98, 620);
				break;
			}
			case 3: {
				image(sceneInputNames, 0, 0);
				fill(0);
				textSize(28);
				textFont(font);
				text("Nombre del jugador #" + index, 289, 357);
				console.display();
				break;
			}
			case 4: {
				if (!loadplayers) {
					try {
						this.loadPlayer();
					} catch (IOException e) {

						e.printStackTrace();
					}
					loadplayers = true;
				}
				// dispaly te board
				image(bg, 0, 0);
				image(dialog, 452, 117);
				// display piece of player 1
				image(piece1, lista.head.piece.posx, lista.head.piece.posy);
				// avatar of player 1
				image(player, 809, 54);
				// Display the money and name of player 1
				fill(0);
				textFont(font);
				textSize(18);
				text(lista.head.name, 812, 69);
				text("$" + lista.head.money, 830, 151);
				// Check the num of players

				switch (numPlayers) {
					case 2: {
						// diece of player 2
						image(piece2, lista.tail.piece.posx, lista.tail.piece.posy);
						// avatar of player 2
						image(player, 809, 192);
						// name of player 2
						text(lista.head.next.name, 812, 207);
						// money of player 2
						text("$" + lista.head.next.money, 830, 289);

						image(turn1L, 775, 88);
						image(turn2L, 775, 230);

						switch (current.turno) {
							case 1:
								image(turn1D, 775, 88);
								break;
							case 2:
								image(turn2D, 775, 230);
								break;
						}
						break;
					}
					case 3: {
						// avatar of player 2
						image(player, 809, 192);
						// avatar player 3
						image(player, 809, 337);
						// display the piece of player 2
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						// name of player 2
						text(lista.head.next.name, 812, 207);
						// money player 2
						text("$" + lista.head.next.money, 830, 289);
						// piece of player 3
						image(piece3, lista.tail.piece.posx, lista.tail.piece.posy);
						// money player 3
						text("$" + lista.head.next.next.money, 830, 434);
						// name player 3
						text(lista.tail.name, 812, 352);

						image(turn1L, 775, 88);
						image(turn2L, 775, 230);
						image(turn3L, 775, 375);

						switch (current.turno) {
							case 1:
								image(turn1D, 775, 88);
								break;
							case 2:
								image(turn2D, 775, 230);
								break;
							case 3:
								image(turn3D, 775, 375);
								break;
						}
						break;
					}
					case 4: {
						// display the piece of player 2
						image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
						// display the piece of player 3
						image(piece3, lista.tail.prev.piece.posx, lista.tail.prev.piece.posy);
						// display the piece of player 4
						image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
						// avatar of player 2
						image(player, 809, 192);
						// avatar of player 3
						image(player, 809, 337);
						// avatar of player 4
						image(player, 809, 482);
						// name of player 2
						text(lista.head.next.name, 812, 207);
						// name of player 3
						text(lista.head.next.next.name, 812, 352);
						// name of player 4
						text(lista.tail.name, 812, 497);
						// money player 2
						text("$" + lista.head.next.money, 830, 289);
						// money player 3
						text("$" + lista.head.next.next.money, 830, 434);
						// money player 4
						text("$" + lista.tail.money, 830, 579);

						image(turn1L, 775, 88);
						image(turn2L, 775, 230);
						image(turn3L, 775, 375);
						image(turn4L, 775, 520);

						switch (current.turno) {
							case 1:
								image(turn1D, 775, 88);
								break;
							case 2:
								image(turn2D, 775, 230);
								break;
							case 3:
								image(turn3D, 775, 375);
								break;
							case 4:
								image(turn4D, 775, 520);
								break;
						}

						break;
					}
				}

				// Display dices
				image(dados[current.result.results[0] - 1], 793, 622);
				image(dados[current.result.results[1] - 1], 869, 622);

				if (current.movearound) {
					try {
						current.movePiece();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (move) {
					if (current.piece.posx == Integer.parseInt(current.coords[0])
							&& current.piece.posy == Integer.parseInt(current.coords[1])) {
						try {
							bank.request("buy", current);
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						move = false;
					}
				}

				if (bank.ofert) {
					if (!current.isPrisoner) {
						fill(0);
						textFont(font);
						textSize(24);
						image(ofert, 145, 253);
						text("¿Deseas comprar " + current.position.name + " por \ntan solo "
								+ current.position.selling_price + " barritas?", 190, 315);
						{
							fill(255, 255, 255);
							text("Sisas", 223, 453);
							text("Nega", 510, 453);
						}
					} else {
						fill(0);
						textFont(font);
						textSize(24);
						image(ofertexitjail, 145, 253);
						text("¿Deseas Salir de le carcel por \ntan solo "
								+ 200000 + " barritas?", 190, 315);
					}
				}

				if (bank.fallontransport) {
					bank.buy = false;
					bank.fallonservices = false;
					fill(0);
					textFont(font);
					textSize(15);
					text(current.name + " le transfiere \n "
							+ current.position.rental_price * current.position.owner.transport + " barras a\n " +
							current.position.owner.name
							+ " porque tiene\n " + current.position.owner.transport
							+ " transportes", 512, 152);
				}

				if (bank.fallonservices) {
					bank.buy = false;
					bank.fallontransport = false;
					fill(0);
					textFont(font);
					textSize(14);
					text(current.name + " paga " + bank.paying + " \na "
							+ current.position.owner.name
							+ " porque tiene " + current.position.owner.services
							+ "\nservicios y el resultado\n de los dados fue " + current.result.result, 510, 152);
				}

				if (bank.pay) {
					bank.fallonservices = false;
					bank.fallontransport = false;
					fill(0);
					textFont(font);
					textSize(15);
					switch (current.position.type) {
						case "taxe": {
							text("Joa no te tocaba, suelta\n" + current.position.rental_price
									+ " barras pa \nlos tombos", 510,
									160);
							break;
						}
						default: {
							textSize(14);
							text(current.name + " transfiere " + current.position.rental_price + " \n barras a "
									+ current.position.owner.name + " por \n meterse en patio ajeno", 508, 160);
							break;
						}
					}
				}
				if (bank.launchluck) {
					image(luck, 228, 306);
					fill(0);
					textFont(font);
					textSize(14);
					text(bank.card.description, 273, 375);
				}

				if (bank.launchArk) {
					image(ark, 228, 306);
					fill(0);
					textFont(font);
					textSize(14);
					text(bank.card.description, 273, 365);
				}
				if (sleep) {
					fill(0);
					textFont(font);
					textSize(15);
					text("Aja papa bello estas dormido\nSacaste doble, tira otra vez", 485, 160);
				}

				if (bank.launchjail) {

					fill(0);
					textFont(font);
					textSize(15);
					text(current.name + " anda salado hoy. \n Una patrulla se lo \n llevó pa' la modelo", 508, 160);
				}
				if (bank.launchwelcome) {
					fill(0);
					textFont(font);
					textSize(14);
					text("Bienvenido a Sincelejo \n como aca no hay nada te damos 810000\nagarra una moto sabiamente",
							508, 160);
				}
				break;
			}
		}
	}

	public void keyPressed() {

		if (teclado.keyAnalyzer(key).compareTo("LETTER") == 0 || teclado.keyAnalyzer(key).compareTo("NUMBER") == 0) {
			console.addChar(key);
		}
		if (keyCode == BACKSPACE) {
			console.deleteChar();
		}

		if (keyCode == ENTER) {
			if (!console.chars.equals("")) {
				if (index <= numPlayers) {
					input[index] = console.chars;
					if (index == numPlayers) {
						scene = 4;
					}

				}
				console.reset();
				index++;
			}
		}
		if (key == ' ' && scene == 1) {
			scene = 2;
		}
	}

	@Override
	public void mouseClicked() {

		if (scene == 2) {
			if (mouseX > 54 && mouseX < 276 && mouseY > 67 && mouseY < 195) {
				numPlayers = 2;
				scene = 3;
			} else if (mouseX > 54 && mouseX < 276 && mouseY > 306 && mouseY < 434) {
				numPlayers = 3;
				scene = 3;
			} else if (mouseX > 54 && mouseX < 276 && mouseY > 545 && mouseY < 673) {
				numPlayers = 4;
				scene = 3;
			}
			console.activate();
		}
		if (scene == 4) {

			if (mouseX > 793 && mouseX < 939 && mouseY > 702 && mouseY < 718) {
				bank.pay = false;
				bank.fallontransport = false;
				if (launch) {
					if (!current.isBroken) {
						if (!current.isPrisoner) {
							if (!changeturn) {
								try {
									image(loadImage("src/images/launch.png"), 794, 704);
									current.moveAround();
									sleep = false;
									move = true;
								} catch (IOException e) {
									e.printStackTrace();
								}
								if (current.result.isPair) {
									changeturn = false;
									current.numpairs++;
								} else {
									launch = false;
									changeturn = true;
									current.numpairs = 0;
								}
								if (current.numpairs == 3) {
									try {
										bank.request("gojail", current);
									} catch (IOException e) {
										e.printStackTrace();
									}
									changeturn = true;
								}
							}
						}
					}
					
				}
			}
			if (mouseX > 793 && mouseX < 939 && mouseY > 729 && mouseY < 745) {
				bank.launchluck = false;
				bank.pay = false;
				bank.fallontransport = false;
				bank.launchArk = false;
				launch = true;
				bank.launchArk = false;
				bank.launchjail = false;
				if (changeturn) {
					image(loadImage("src/images/change.png"), 794, 731);
					current = current.next;
					current.result.results = current.prev.result.results;
					changeturn = false;
					if (current.isPrisoner) {
						try {
							current.playInJail();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}

				} else {
					sleep = true;
				}
			}
			if (bank.ofert && mouseX > 195 && mouseX < 283 && mouseY > 422 && mouseY < 472) {
				if (!current.isPrisoner) {
					try {
						bank.tranferProperty(current.position, current);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						bank.request("exitjail", current);
						changeturn = true;
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				bank.ofert = false;
			} else if (bank.ofert && mouseX > 486 && mouseX < 574 && mouseY > 422 && mouseY < 472) {
				if (current.isPrisoner) {
					current.rollDices();
					if (current.result.isPair) {
						try {
							bank.request("exitjailfree", current);
						} catch (IOException e) {

							e.printStackTrace();
						}
						try {
							current.moveAround(true);
						} catch (IOException e) {

							e.printStackTrace();
						}
					} else {
						current.turnsInJail++;
						System.out.println("f");
						current = current.next;
						changeturn = false;
					}
				}
				bank.ofert = false;
			}

			if ((bank.launchluck || bank.launchArk) && mouseX > 357 && mouseX < 440 && mouseY > 439 && mouseY < 464) {
				bank.launchluck = false;
				bank.launchArk = false;
				current.piece.posx = Integer.parseInt(current.coor[current.position.index].split(",")[0]);
				current.piece.posy = Integer.parseInt(current.coor[current.position.index].split(",")[1]);
				current.movearound = true;
			}
		}

	}

	public void loadPlayer() throws IOException {

		Player player1 = new Player(input[1], bank.board, bank);
		lista.addPlayer(player1);
		switch (numPlayers) {
			case 2: {
				Player player2 = new Player(input[2], bank.board, bank);
				lista.addPlayer(player2);
				break;
			}
			case 3: {
				Player player2 = new Player(input[2], bank.board, bank);
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank);
				lista.addPlayer(player3);

				break;
			}
			case 4: {
				Player player2 = new Player(input[2], bank.board, bank);
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank);
				lista.addPlayer(player3);
				Player player4 = new Player(input[4], bank.board, bank);
				lista.addPlayer(player4);
				break;
			}
		}
		lista.asingcoords();
		lista.display();
		current = lista.head;
		System.out.println("\n" + current.turno);

	}

	public void run() {
		String[] processingArgs = { this.getClass().getName() };
		PApplet.runSketch(processingArgs, this);
	}

}

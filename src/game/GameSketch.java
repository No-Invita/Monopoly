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
	int scene = 1, numPlayers = 1, index = 1, av, subescena;

	String[] input = new String[5];
	String[] avatars = { "luis.png", "uso.png", "diome.png", "gel.png" };
	String[] chooseavatars = new String[4];
	PImage[] dados = new PImage[6];

	PImage bg, sceneStart, sceneChoosePLayers, sceneInputNames, ofert, player;

	PImage piece1, piece2, piece3, piece4, dialog, ark, turn1D, turn2D, turn3D, turn4D,
			ofertexitjail, turn1L, turn2L, turn3L, turn4L, showownerships, luck, bars, chooseAvatar, selection,
			eliminated, lucho, gel, diome, uso, orden;

	boolean changeturn = false, loadplayers = false, move = false, launch = true, sleep = false,
			ownerships = false, showplayer1 = false, showplayer2 = false, showplayer3 = false, showplayer4;

	Console console = new Console(360, 386, 24, this);
	Keyboard teclado = new Keyboard(this, console);
	PlayerList lista;
	Bank bank;
	Player current;
	PFont font;

	private boolean showorden = true;

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
		chooseAvatar = loadImage("src/images/chooseavatars.png");
		selection = loadImage("src/images/choose.png");
		eliminated = loadImage("src/images/delete.png");
		showownerships = loadImage("src/images/show.png");
		bars = loadImage("src/images/jail.png");
		orden = loadImage("src/images/orden.png");
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
				if (subescena == 1) {
					image(chooseAvatar, 0, 0);
					textFont(font);
					textSize(33);
					text("SELECCIONA TU AVATAR", 330, 90);
					switch (av) {
						case 1:
							image(selection, 173, 164);
							break;
						case 2:
							image(selection, 522, 165);
							break;
						case 3:
							image(selection, 173, 433);
							break;
						case 4:
							image(selection, 522, 433);
							break;

					}
					break;
				}
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

				// display board
				image(bg, 0, 0);
				image(dialog, 452, 117);
				if (showorden) {
					image(orden, 223, 117);
					fill(0);
					textFont(font);
					textSize(22);
					text("Por alguna razón del destino \nel orden a jugar es: \n"+lista.display(), 273, 300);
				}
				// player 1
				fill(0);
				textFont(font);
				textSize(18);
				image(player, 809, 54);
				if (!lista.head.isBroken) {
					image(piece1, lista.head.piece.posx, lista.head.piece.posy);
					text("$" + lista.head.money, 830, 151);
					image(turn1L, 775, 88);

				} else {
					image(eliminated, 817, 99);
				}
				text(lista.head.name, 812, 69);
				image(loadImage(lista.head.avatar), 810, 73);

				switch (numPlayers) {
					case 2: {
						// player 2
						image(player, 809, 192);
						if (!lista.tail.isBroken) {
							image(piece2, lista.tail.piece.posx, lista.tail.piece.posy);
							text("$" + lista.tail.money, 830, 289);
							image(turn2L, 775, 230);

						} else {
							image(eliminated, 817, 231);
						}
						text(lista.tail.name, 812, 207);
						image(loadImage(lista.tail.avatar), 810, 211);

						switch (current.turno) {
							case 1:
								image(turn1D, 775, 88);
								break;
							case 2:
								image(turn2D, 775, 230);
								break;
						}
						if (lista.head.isPrisoner) {
							image(bars, 809, 72);
						}
						if (lista.tail.isPrisoner) {
							image(bars, 809, 210);
						}

						break;
					}
					case 3: {
						// Player 2
						image(player, 809, 192);
						if (!lista.head.next.isBroken) {
							image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
							text("$" + lista.head.next.money, 830, 289);
							image(turn2L, 775, 230);
						} else {
							image(eliminated, 817, 231);
						}
						text(lista.head.next.name, 812, 207);
						image(loadImage(lista.head.next.avatar), 810, 211);

						// player 3
						image(player, 809, 337);
						if (!lista.tail.isBroken) {
							image(piece3, lista.tail.piece.posx, lista.tail.piece.posy);
							text("$" + lista.tail.money, 830, 434);
							image(turn3L, 775, 375);
						} else {
							image(eliminated, 817, 376);
						}
						text(lista.tail.name, 812, 352);
						image(loadImage(lista.tail.avatar), 810, 211);

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
						if (lista.head.isPrisoner) {
							image(bars, 809, 72);
						}
						if (lista.head.next.isPrisoner) {
							image(bars, 809, 210);
						}
						if (lista.tail.isPrisoner) {
							image(bars, 809, 355);
						}

						break;
					}
					case 4: {
						// player 2
						image(player, 809, 192);
						if (!lista.head.next.isBroken) {
							image(piece2, lista.head.next.piece.posx, lista.head.next.piece.posy);
							text("$" + lista.head.next.money, 830, 289);
							image(turn2L, 775, 230);
						} else {
							image(eliminated, 817, 231);
						}
						text(lista.head.next.name, 812, 207);
						image(loadImage(lista.head.next.avatar), 810, 211);

						// player 3
						image(player, 809, 337);
						if (!lista.tail.prev.isBroken) {
							image(piece3, lista.tail.prev.piece.posx, lista.tail.prev.piece.posy);
							text("$" + lista.tail.prev.money, 830, 434);
							image(turn3L, 775, 375);
						} else {
							image(eliminated, 817, 376);
						}
						text(lista.tail.prev.name, 812, 352);
						image(loadImage(lista.tail.prev.avatar), 810, 356);

						// player 4
						image(player, 809, 482);
						if (!lista.tail.isBroken) {
							image(piece4, lista.tail.piece.posx, lista.tail.piece.posy);
							text("$" + lista.tail.money, 830, 579);
							image(turn4L, 775, 520);
						} else {
							image(eliminated, 817, 521);
						}
						text(lista.tail.name, 812, 497);
						image(loadImage(lista.tail.avatar), 810, 501);

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
						if (lista.head.isPrisoner) {
							image(bars, 809, 72);
						}
						if (lista.head.next.isPrisoner) {
							image(bars, 809, 210);
						}
						if (lista.tail.prev.isPrisoner) {
							image(bars, 809, 355);
						}
						if (lista.tail.isPrisoner) {
							image(bars, 809, 501);
						}

						break;

					}
				}
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
						if (!current.result.isPair) {
							changeturn = true;
						} else {
							launch = true;
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
						text("¿Deseas comprar " + current.position.name + " por \ntan solo $"
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
						text("¿Deseas Salir de le carcel por \ntan solo $200.000 barritas?", 190, 315);
						{
							fill(255, 255, 255);
							text("Sisas", 223, 453);
							text("Nega", 510, 453);
						}
					}
				}
				if (current.launch_ofert_with_card) {
					fill(0);
					textFont(font);
					textSize(24);
					image(ofertexitjail, 145, 253);
					text("¿Deseas utilizar la tarjeta \npara salir de la carcél?", 190, 315);
				}

				if (bank.fallontransport) {
					bank.buy = false;
					bank.fallonservices = false;
					fill(0);
					textFont(font);
					textSize(15);
					text(current.name + " le transfiere \n$ "
							+ current.position.rental_price * current.position.owner.transport + " barras a\n "
							+
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
					text(current.name + " paga $" + bank.paying + " \na "
							+ current.position.owner.name
							+ " porque tiene " + current.position.owner.services
							+ "\nservicios y el resultado\n de los dados fue " + current.result.result, 510,
							152);
				}

				if (bank.pay) {
					bank.fallonservices = false;
					bank.fallontransport = false;
					fill(0);
					textFont(font);
					textSize(15);
					switch (current.position.type) {
						case "taxe": {
							text("Joa no te tocaba, suelta\n$" + current.position.rental_price
									+ " barras pa \nlos tombos", 510,
									160);
							break;
						}
						default: {
							textSize(14);
							text(current.name + " transfiere $" + current.position.rental_price
									+ " \n barras a "
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
					text(current.name + " anda salado hoy. \n Una patrulla se lo \n llevó pa' la modelo", 508,
							160);
				}
				if (bank.launchwelcome && !bank.ofert) {
					fill(0);
					textFont(font);
					textSize(14);
					text("Bienvenido a Sincelejo. \nComo aquí no hay nada te \ndamos $810.000, coge una \nmoto sabiamente",
							508, 152);
				}
				if (bank.luckexitjail) {
					fill(0);
					textFont(font);
					textSize(14);
					text("Joa tu si eres de buena\n Sales de la carcel\npor sacar doble", 508, 160);
				}
				if (bank.exitjailoverturn) {
					fill(0);
					textFont(font);
					textSize(14);
					text("Ya no te queremos aquí\n Sales de la carcel \npagando $200.000", 508, 160);
					launch = true;
				}
				if (showplayer1) {
					image(showownerships, 223, 117);
					fill(0);
					textFont(font);
					textSize(20);
					text(lista.head.showownerships(), 300, 300);
				}
				if (showplayer2) {
					image(showownerships, 223, 117);
					fill(0);
					textFont(font);
					textSize(20);
					text(lista.head.next.showownerships(), 300, 300);
				}
				if (showplayer3) {
					image(showownerships, 223, 117);
					fill(0);
					textFont(font);
					textSize(20);
					text(lista.tail.prev.showownerships(), 300, 300);
				}
				if (showplayer4) {
					image(showownerships, 223, 117);
					fill(0);
					textFont(font);
					textSize(20);
					text(lista.tail.showownerships(), 300, 300);
				}
				if (current.isBroken == true) {
					fill(0);
					textFont(font);
					textSize(15);
					text(current.name + " quedó pelao'. \nSe va pa su casa", 508, 160);
				}
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
					console.reset();
					subescena = 1;
				} else {
					scene = 4;
				}

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
			if (mouseX > 336 && mouseX < 336 + 152 && mouseY > 582 && mouseY < 582 + 37) {
				showorden = false;
			}
			if (mouseX > 793 && mouseX < 939 && mouseY > 702 && mouseY < 718) {
				bank.launchluck = false;
				bank.pay = false;
				bank.fallontransport = false;
				bank.launchArk = false;
				bank.launchluck = false;
				bank.launchjail = false;
				bank.launchexitjailwithcard = false;
				bank.launchwelcome = false;
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
									current.numpairs = 0;
								}
								if (current.numpairs == 3) {
									try {
										bank.request("gojail", current);
									} catch (IOException e) {
										e.printStackTrace();
										changeturn = true;
									}

								}
							}
						}
					}
					launch = false;

				}
			}
			if (mouseX > 793 && mouseX < 939 && mouseY > 729 && mouseY < 745) {
				bank.launchluck = false;
				bank.pay = false;
				bank.fallontransport = false;
				bank.launchluck = false;
				bank.launchArk = false;
				bank.launchjail = false;
				bank.launchexitjailwithcard = false;
				bank.launchwelcome = false;
				if (changeturn) {
					launch = true;
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
					if (current.result.isPair) {
						sleep = true;
					}

				}
			}
			if (bank.launchexitjailwithcard && mouseX > 195 && mouseX < 283 && mouseY > 422 && mouseY < 472) {
				try {
					bank.request("exitjailwithcard", current);
				} catch (IOException e) {
					e.printStackTrace();
				}
				bank.launchexitjailwithcard = false;
			} else if (bank.launchexitjailwithcard && mouseX > 486 && mouseX < 574 && mouseY > 422 && mouseY < 472) {
				current.rollDices();
				if (current.result.isPair) {
					try {
						bank.request("exitjailfree", current);
						move = true;
					} catch (IOException e) {

						e.printStackTrace();
					}
				} else {
					current.turnsInJail++;
					System.out.println("f");

				}
				bank.launchexitjailwithcard = false;
				changeturn = true;

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
						move = true;
						if (!current.result.isPair) {
							changeturn = true;
						}
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
							move = true;
							bank.request("exitjailfree", current);
						} catch (IOException e) {

							e.printStackTrace();
						}
					} else {
						current.turnsInJail++;
						System.out.println("f");
						// current = current.next;
					}
					changeturn = true;
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

			if (mouseX > 809 && mouseX < 809 + 118 && mouseY > 154 && mouseY < 154 + 18) {
				if (!showplayer1 && lista.head.num_properties > 0) {
					showplayer1 = true;
				} else {
					showplayer1 = false;
				}

			}

			if (mouseX > 809 && mouseX < 809 + 118 && mouseY > 292 && mouseY < 292 + 18) {
				if (!showplayer2 && lista.head.next.num_properties > 0) {
					showplayer2 = true;
				} else {
					showplayer2 = false;
				}
			}

			if (mouseX > 809 && mouseX < 809 + 118 && mouseY > 437 && mouseY < 437 + 18) {
				if (!showplayer3 && lista.tail.prev.num_properties > 0) {
					showplayer3 = true;
				} else {
					showplayer3 = false;
				}
			}

			if (mouseX > 809 && mouseX < 809 + 118 && mouseY > 582 && mouseY < 582 + 18) {
				if (!showplayer4 && lista.tail.num_properties > 0) {
					showplayer4 = true;
				} else {
					showplayer4 = false;
				}
			}

		}
		if (subescena == 1) {
			if (mouseX > 181 && mouseX < 181 + 250 && mouseY > 172 && mouseY < 172 + 155) {
				av = 1;
			} else if (mouseX > 530 && mouseX < 530 + 250 && mouseY > 172 && mouseY < 172 + 156) {
				av = 2;
			} else if (mouseX > 181 && mouseX < 181 + 250 && mouseY > 441 && mouseY < 441 + 155) {
				av = 3;
			} else if (mouseX > 530 && mouseX < 530 + 250 && mouseY > 441 && mouseY < 441 + 155) {
				av = 4;
			}
			// accep button
			if (mouseX > 428 && mouseX < 428 + 104 && mouseY > 670 && mouseY < 670 + 37) {
				chooseavatars[index - 1] = avatars[av - 1];
				index++;
				subescena = 0;

				if (index == numPlayers + 1) {
					scene = 4;
				}
			}

		}

	}

	public void loadPlayer() throws IOException {

		Player player1 = new Player(input[1], bank.board, bank, chooseavatars[0]);
		lista.addPlayer(player1);
		switch (numPlayers) {
			case 2: {
				Player player2 = new Player(input[2], bank.board, bank, chooseavatars[1]);
				lista.addPlayer(player2);
				break;
			}
			case 3: {
				Player player2 = new Player(input[2], bank.board, bank, chooseavatars[1]);
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, chooseavatars[2]);
				lista.addPlayer(player3);

				break;
			}
			case 4: {
				Player player2 = new Player(input[2], bank.board, bank, chooseavatars[1]);
				lista.addPlayer(player2);
				Player player3 = new Player(input[3], bank.board, bank, chooseavatars[2]);
				lista.addPlayer(player3);
				Player player4 = new Player(input[4], bank.board, bank, chooseavatars[3]);
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

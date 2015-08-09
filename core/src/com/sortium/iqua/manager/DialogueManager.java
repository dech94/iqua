package com.sortium.iqua.manager;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sortium.iqua.Dialogue;
import com.sortium.iqua.IquaGame;
import com.sortium.iqua.NPC;
import com.sortium.iqua.Response;
import com.sortium.iqua.Sentence;
import com.sortium.iqua.Status;
import com.sortium.iqua.event.EventEngine;
import com.sortium.iqua.event.GetEvent;

public class DialogueManager extends Manager<Dialogue>
{

	public DialogueManager(IquaGame game)
	{
		super(game, "dialogue");
	}
	
	public void save(Dialogue d)
	{
		
	}
	
	public void load(Dialogue d)
	{
		
	}
	
	public void run(Dialogue d)
	{
		EventEngine.get().trigger("scene.dialogue", new GetEvent<Dialogue>(d) );
	}
	
	public void runIntro() // TEST
	{
		NPC n = new NPC("Test", "Test", 'M', Status.Villager,
				new Texture(Gdx.files.internal("images/Characters/hikari.png")));
		
		Dialogue dialog = new Dialogue(n);

		ArrayList<Response> ac = new ArrayList<Response>();
		Sentence sc = new Sentence("Elle m'a dit qu'elle avait jouer du côté de la plage aujourd'hui... "
				+ "Tu pourrais allez voir là-bas.Reviens me voir si tu retrouves la poupée. "
				+ "Moi je vais rester ici et fouiller les environs... A tout à l'heure !", ac);
		ac.add(new Response(""));
		
		ArrayList<Response> ab = new ArrayList<Response>();
		Sentence sb = new Sentence("Ma petite soeur, Mia, a perdu sa poupée... "
				+ "Elle était inconsolable, alors je lui ai promis de la retrouver."
				+ " Cherches la avec moi, s'il-te-plait !", ab);
		ab.add(new Response("Tu as une petite idée d'où elle peut se trouver ?", sc));
		ab.add(new Response("Mais elle pourrait être n'importe où !", sc));
		ab.add(new Response("Ok, on commence par où ?", sc));
		
		ArrayList<Response> aa = new ArrayList<Response>();
		Sentence sa = new Sentence("Oh si, s'il-te-plait, ne me laisse pas tomber ! De tout façon, "
				+ "tu n'as vraiment rien de mieux à faire ! Et tu ne risques rien, "
				+ "puisque tu dors...", aa);
		aa.add(new Response("C'est vrai ? C'est d'accord ! Dis moi ce que je peux faire.", sb));
		aa.add(new Response("Je n'ai surtout pas l'impression d'avoir le choix...", sb));
		
		ArrayList<Response> a6 = new ArrayList<Response>();
		Sentence s6 = new Sentence("Oui, bon en fait, j'ai un E-NOR-ME soucis, il faut ABSOLUMENT "
				+ "que tu m'aides...", a6);
		a6.add(new Response("Je ne suis pas très sûr, là...", sa));
		a6.add(new Response("Qu'est-ce qui t'arrive ?", sb));
		
		ArrayList<Response> a5 = new ArrayList<Response>();
		Sentence s5 = new Sentence("Oui, en gros, c'est un rêve que t'envoie la directrice de "
				+ "l'Académie des Crapauds du Lac, uneacadémie de magie qui se trouve à Gerall... "
				+ "Ne me demande pas pourquoi toi, ni pourquoi cerêve, j'en sais rien ! "
				+ "En plus, j'ai vraiment pas le temps de t'expliquer, "
				+ "j'ai un problème beaucoup plus urgent...", a5);
		a5.add(new Response("Je suppose que je comprendrais mieux par la suite...", s6));
		a5.add(new Response("Oui, bon c'est quoi ton problème si urgent ?", s6));
		a5.add(new Response("Merci, beaucoup pour cette explication. A mon tour de t'aider !", s6));
		
		ArrayList<Response> a4 = new ArrayList<Response>();
		Sentence s4 = new Sentence("Drôlement perspicace et pas curieux pour un sous. J'adore !", a4);
		a4.add(new Response("Je ne crois pas qu'il y ait souvent beaucoup de logique dans les rêves...", s6));
		a4.add(new Response("Ca promet d'être intéressant. C'est quoi la suite ?", s6));
		a4.add(new Response("J'ai surtout envie de vite en finir.", s6));
		
		
		ArrayList<Response> a3 = new ArrayList<Response>();
		Sentence s3 = new Sentence("Oui, c'est vrai que ça peut être un peu perturbant au début. Mais, en fait, tu es tout simplement entrain de rêver. Du calme !", a3);
		a3.add(new Response("Entrain de rêver ? Comment ça ?", s5));
		a3.add(new Response("J'ai du mal comprendre. Répète ?", s5));
		a3.add(new Response("J'aurais dû y penser tout de suite. Et donc ?", s6));
		
		ArrayList<Response> a2 = new ArrayList<Response>();
		Sentence s2 = new Sentence("Ne t'inquiète pas. Tu es dans un rêve, et moi je suis là pour te guider.", a2);
		a2.add(new Response("Dans un rêve ? Mais pourquoi ?", s5));
		a2.add(new Response("Tu peux répêter ?", s5));
		a2.add(new Response("Ah d'accord, très bien. Je t'écoute.", s6));

		
		ArrayList<Response> a1 = new ArrayList<Response>();
		Sentence s1 = new Sentence("Bah, non, forcément. C'est la première fois que tu rêves de moi. Je m'appelle Hikari !", a1);
		a1.add(new Response("Donc... Je suis entrain de rêver, c'est ça ?", s5));
		a1.add(new Response("Je te demande pardon ?!", s5));
		a1.add(new Response("Ah, bon bien sûr, soit. Bonjour Hikari.", s6));
		
		ArrayList<Response> a0 = new ArrayList<Response>();
		Sentence s0 = new Sentence("Ah, enfin te voilà ! Je t'ai cherché partout...", a0);
		a0.add(new Response("Minute ! Je te connais pas, toi. T'es qui ?", s1));
		a0.add(new Response("Non, mais attends, je suis où là ?", s2));
		a0.add(new Response("Qu'est ce qui m'est arrivé? Je ne me souviens de rien..", s3));
		a0.add(new Response("Je suis entrain de rêver, c'est ça ?", s4));

		dialog.addSentences(s0);
		dialog.addSentences(s1);
		dialog.addSentences(s2);
		dialog.addSentences(s3);
		dialog.addSentences(s4);
		dialog.addSentences(s5);
		dialog.addSentences(s6);
		dialog.addSentences(sa);
		dialog.addSentences(sb);
		dialog.addSentences(sc);
		
		run(dialog);
	}

}

package org.example.game.steering;

/** SteeringBehavior's permitem controlar o movimento de um personagem ({@code Steerable}),
 * aplicando sobre ele "forças". 
 * 	Ótimo tutorial: http://gamedevelopment.tutsplus.com/series/understanding-steering-behaviors--gamedev-12732*/
public interface SteeringBehavior {
	public void apply(Steerable character);
}

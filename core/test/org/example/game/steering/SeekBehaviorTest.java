package org.example.game.steering;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.example.test.helper.VectorHelper;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;

public class SeekBehaviorTest {
	private static final Vector2 anyVel = new Vector2(1f,2f);
	private SeekBehavior seekBehaviour;
	private ControlledCharacter character;
	float velocityMag = 5f, deltaVelocityMag = 1f;
	
	@Before
	public void setup(){
		seekBehaviour = new SeekBehavior();
		seekBehaviour.setTarget(new Vector2(10f,10f));
		
		character = mock(ControlledCharacter.class);
		
		resetCharacter();
	}

	private void resetCharacter() {
		when(character.getVelocityMag()).thenReturn(velocityMag);
		when(character.getPosition()).thenReturn(Vector2.Zero);
		when(character.getDeltaVelocity()).thenReturn(deltaVelocityMag);
		when(character.getVelocity()).thenReturn(anyVel);
	}
	
	@Test
	public void applySteering_shouldCallCharacterApplyForce(){
		seekBehaviour.apply(character);
		
		Mockito.verify(character)
			   .applyForce(Mockito.any(Vector2.class));
	}
	
	@Test
	public void testSeekDirection() {
		checkSeekDir(new Vector2(0f, 0f), seekBehaviour.getTarget().cpy().nor());
		checkSeekDir(seekBehaviour.getTarget(), new Vector2(0f, 0f));
		checkSeekDir(new Vector2(10f, 0f), new Vector2(0f, 1f));
	}
	
	@Test
	public void calculateSeekVelocity_ShouldKeepTheDesiredVelocityMagnitude(){
		Vector2 desiredVelocity = seekBehaviour.calculateSeekVelocity(character);

		assertEquals(velocityMag, desiredVelocity.len(), VectorHelper.EPSILON);
		assertThat(desiredVelocity.nor(), is(equalTo(seekBehaviour.calculateSeekDir(character))));
	}

	@Test
	public void calculatedSteeringForceMagnitude_shouldBeLimitedByTheMaxForce(){
		Vector2 steeringForce = seekBehaviour.calculateSteeringForce(character);

		assertThat(steeringForce.len() - VectorHelper.EPSILON
					, is(lessThanOrEqualTo((double)deltaVelocityMag)));
	}
	@Test
	public void calculatedSteeringDir_shouldDirectVelocityToDesiredVelocity(){
		Vector2 desiredVel = seekBehaviour.calculateSeekVelocity(character);
		Vector2 expectedSteeringDir = desiredVel.sub(character.getVelocity()).nor();
		
		Vector2 steeringDir = seekBehaviour.calculateSteeringDir(character);
		
		assertThat(steeringDir
					, is(equalTo(expectedSteeringDir)));
	}
	
	private void checkSeekDir(Vector2 characterPos, Vector2 expectedDir) {
		when(character.getPosition()).thenReturn(characterPos);
		
		Vector2 seekDir = seekBehaviour.calculateSeekDir(character);
		
		assertThat(seekDir, not(sameInstance(seekBehaviour.getTarget())));
		assertThat(seekDir + " == " + expectedDir
					, VectorHelper.isEquals(expectedDir, seekDir), is(true));
	}

}

package org.example.game.steering;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.badlogic.gdx.math.Vector2;

public class PathFollowingBehaviorTest {
	private static final Vector2 anyVel = new Vector2(1f,2f);
	private PathFollowingBehavior behaviour;
	private ControlledCharacter character;
	private float velocityMag = 5f, deltaVelocityMag = 1f;
	
	private List<Vector2> path;
	private float reachDistance = 1f;
	
	@Before
	public void setup(){
		behaviour = new PathFollowingBehavior();
		character = mock(ControlledCharacter.class);
		
		path = Arrays.asList(new Vector2(0,0), new Vector2(5,0), new Vector2(5,7)
							, new Vector2(5,0));
		
		behaviour.setPath(path);
		behaviour.setReachDistance(reachDistance);
	}

	@Test
	public void PathFollowingInitialTarget_ShouldBeTheFirst(){
		assertEquals(0, behaviour.getTargetIndex());
	}

	@Test
	public void whenCharacterReachTheMinDistanceFromTarget_PathFollowingShouldGoToNextTarget() {
		given_characterReachFirstTarget();
		
		behaviour.verifyTarget(character);
		
		assertEquals(1, behaviour.getTargetIndex());
	}

	@Test
	public void whenCharacterReachFinalTarget_PathFollowingShouldStop(){
		given_characterReachLastTarget();
		
		behaviour.verifyTarget(character);
		
		assertEquals(path.size() - 1, behaviour.getTargetIndex());
	}

	@Test
	public void apply_shouldApplyForceToCharacter(){
		given_characterHasSomeVelocityAndPosition();
	
		behaviour.apply(character);
		
		Mockito.verify(character).applyForce(Mockito.any(Vector2.class));
	}
	
	@Test
	public void apply_shouldStopApplyForce_WhenReachLastTarget(){
		given_characterHasSomeVelocityAndPosition();
		given_characterReachLastTarget();
		
		behaviour.apply(character);
		
		Mockito.verify(character, Mockito.never()).applyForce(Mockito.any(Vector2.class));
	}

	/* **************************************************************************************/
	private void given_characterHasSomeVelocityAndPosition() {
		when(character.getPosition()).thenReturn(Vector2.Zero);
		when(character.getVelocity()).thenReturn(anyVel);
	}
	
	private void given_characterReachFirstTarget() {
		Vector2 charPosition = path.get(0).cpy().sub(new Vector2(-1,0).scl(0.9f * reachDistance));
		when(character.getPosition()).thenReturn(charPosition);
	}

	private void given_characterReachLastTarget() {
		Vector2 charPosition = path.get(path.size() - 1).cpy();
		when(character.getPosition()).thenReturn(charPosition);
		behaviour.setTargetIndex(path.size() - 1);
	}
}

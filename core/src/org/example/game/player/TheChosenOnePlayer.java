/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.example.game.player;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.List;
import org.example.game.drawable.DimensionedTexture;
import org.example.game.pathfinding.MapNode;
import org.example.game.pathfinding.NodeFinder;
import org.example.game.pathfinding.Pathfinder;
import org.example.game.steering.PathFollowingBehavior;
import org.example.game.steering.SteeringBehavior;
import org.example.game.utils.GetNodeOnClick;

/**
 *
 * @author alisonbnt
 */
public class TheChosenOnePlayer extends GamePlayer implements GetNodeOnClick.GetNodeListener{
    
    private NodeFinder finder;

    public TheChosenOnePlayer(DimensionedTexture playerImg) {
        super(playerImg);
    }

    @Override
    public void onGetNode(MapNode node) {
        Pathfinder pathfinder = new Pathfinder();
        Vector2 startPos = getCharacter().getPosition();
        
        MapNode currentNode = getFinder().findNode(startPos);
        
        List<MapNode> path = pathfinder.findPath(getFinder().getGraph(), currentNode, node);
        List<Vector2> pathVector = new ArrayList<Vector2>();
        
        for(MapNode nnode :path){
            pathVector.add(new Vector2(nnode.getX(), nnode.getY()));
        }
        
        setBehavior(new PathFollowingBehavior(pathVector));
    }

    @Override
    public void update(float deltaT) {
        super.update(deltaT); //To change body of generated methods, choose Tools | Templates.
        SteeringBehavior behavior = getBehavior();
        if(behavior instanceof PathFollowingBehavior){
            PathFollowingBehavior pathBehavior = (PathFollowingBehavior) behavior;
            if(pathBehavior.isLastTarget() && pathBehavior.hasReachedTarget(getCharacter())){
                setBehavior(null);
                getCharacter().getVelocity().set(0, 0);
            }
        }
    }

    /**
     * @return the finder
     */
    public NodeFinder getFinder() {
        return finder;
    }

    /**
     * @param finder the finder to set
     */
    public void setFinder(NodeFinder finder) {
        this.finder = finder;
    }

    public void setPosition(float f, float f0) {
        getCharacter().getPosition().set(new Vector2(f, f0));
    }
    
    
    
}

package game;

import com.base.engine.components.GameComponent;
import com.base.engine.components.InteractableCollision;
import com.base.engine.components.MeshRenderer;
import com.base.engine.components.attachments.Interactable;
import com.base.engine.core.GameObject;
import com.base.engine.core.Shapes;
import com.base.engine.core.World;
import com.base.engine.core.math.Vector3f;
import com.base.engine.physics.collision.Collider;
import com.base.engine.physics.collision.Sphere;
import com.base.engine.rendering.Material;
import com.base.engine.rendering.Mesh;
import com.base.engine.rendering.RenderGenerator;
import com.base.engine.rendering.Texture;

public class JumpPad extends GameObject
{
	Interact interact;
	TestPlanet connect;
	private static RenderGenerator generator = new RenderGenerator(null);
	
	public JumpPad(TestPlanet planet)
	{
		Material mat = new Material();
		mat.addTexture("diffuse", new Texture("JumpPad001.png"));
		MeshRenderer renderer = new MeshRenderer(new Mesh("JumpPad001.obj"), mat);
		
		this.connect = planet;
		interact = new Interact(new Sphere(2));
		this.addComponent(renderer);
		this.addComponent(interact);	
	}
	
	private class Interact extends InteractableCollision implements Interactable
	{

		public Interact(Collider collider) {
			super(collider);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void interact() {
			Player player = ((Player)World.world.focus);
			player.setPlanet(connect.planet);;
			player.body.addVelocity(connect.getPosition().sub(player.getPosition()).normal().mul(1000));
		}	
	}
}

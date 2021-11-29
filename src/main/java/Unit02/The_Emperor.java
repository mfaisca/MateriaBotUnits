package Unit02;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.BestAbilities;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import Shared.Methods;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Emperor")
public class The_Emperor extends Unit{
	public The_Emperor() { super("The Emperor"); }
	
	@Override
	public BestAbilities getAbility(AttackName type, Region region) {
		BestAbilities ret = null;
		switch(type) {
			case BRV:
				ret = new BestAbilities(this, 9788, 9788); break;
			case HP:
				ret = new BestAbilities(this, 9789, 9789); break;
			case LD:
				ret = new BestAbilities(this, 11591, 11591); break;
			case BT:
				ret = Region.GL.equals(region) ? new BestAbilities(this, 11234, 11234, 11649) : 
												 new BestAbilities(this, 16610, 11234, 11649, 16610, 16611); break;
			case CALD:
				ret = new BestAbilities(this, 11844, 11844); break;
			default:
				break;
		}
		if(ret == null)
			return super.getAbility(type, region);
		if(ret.getAbilities().isEmpty())
			ret.getAbilities().addAll(super.getAbility(type, region).getAbilities());
		return ret;
	}
	
	@Override
	public void loadFixGL() {
		//Ailment get for easier access
		Ailment aS1 = this.getSpecificAilment(413);
		Ailment aS2 = this.getSpecificAilment(415);
		Ailment aEX = this.getSpecificAilment(417);
		Ailment aBT = this.getSpecificAilment(2431);
		//AA Rank Fix
		this.getSpecificAilment(1245).setRank(2);
		//LD Call Name Fix
		this.getSpecificAilment(11782).setName(new Text(aS1.getName().getBest() + " (C)"));
		this.getSpecificAilment(11595).setName(new Text(aS2.getName().getBest() + " (C)"));
		this.getSpecificAilment(11784).setName(new Text(aEX.getName().getBest() + " (C)"));
		this.getSpecificAbility(11844).getAilments().addAll(this.getSpecificAbility(11595).getAilments());
		//Fix Trap Names
		this.getSpecificAbility(6029).setName(aS1.getName());
		this.getSpecificAbility(6043).setName(aS2.getName());
		this.getSpecificAbility(6059).setName(aEX.getName());
		this.getSpecificAbility(11649).setName(new Text(aBT.getName().getBest() + " (BT Only)"));
		//Trap Triggering
		Ability a = this.getSpecificAbility(11641); //S1
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest()) + " if active on target"));
		a = this.getSpecificAbility(11647); //S2
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest()) + " if active on target"));
		a = this.getSpecificAbility(9788); //BRV
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aEX.getName().getBest()) + " if active on target"));
		a = this.getSpecificAbility(9789); //HP
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aEX.getName().getBest()) + " if active on target"));
		a = this.getSpecificAbility(6056); //EX
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aEX.getName().getBest()) + " if active on target"));
		a = this.getSpecificAbility(11591); //LD
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest())));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest())));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aEX.getName().getBest())));
		a = this.getSpecificAbility(11844); //CALD
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest())));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest())));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aEX.getName().getBest())));
	}
	@Override
	public void loadFixJP() {
		loadFixGL();
		this.getSpecificAilment(3558).setRank(2);
		Ailment aS1 = this.getSpecificAilment(413);
		Ailment aS2 = this.getSpecificAilment(415);
		Ailment aEX = this.getSpecificAilment(417);
		Ailment aBT = this.getSpecificAilment(2431);
		//Fix Trap Names
		this.getSpecificAbility(16607).setName(aEX.getName());
		this.getSpecificAbility(16611).setName(new Text(aBT.getName().getBest() + " (BT+ 2/3)"));
		//Trap Triggering
		Ability a = this.getSpecificAbility(16604);
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS2.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aS1.getName().getBest()) + " if active on target"));
		a.getHitData().add(new HitData(a, "Trigger " + Methods.enframe(aEX.getName().getBest()) + " if active on target"));
	}
}
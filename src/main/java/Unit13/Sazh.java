package Unit13;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Sazh")
public class Sazh extends Unit{
	public Sazh() {
		super("Sazh");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8637};
				break;
			case HP:
				ids = new int[]{8638};
				break;
			case S1:
				ids = new int[]{8634, 8795};
				break;
			case S2:
				ids = new int[]{8636};
				break;
			case EX:
				ids = new int[]{8641};
				break;
			case AA:
				ids = new int[]{3929};
				break;
			case LD:
				ids = new int[]{};
				break;
			case BT:
				ids = new int[]{};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i)).collect(Collectors.toList());
	}
	
	private boolean s2 = false, ex = false;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 8634:
				ret.removeAilmentById(55);
				ret.removeAilmentById(56);
				break;
			case 8795:
				ret.addStaticHit("Enabled when target is debuffed", 0);
				break;
			case 8636:
				if(!s2) {
					s2 = true;
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 120, 150);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 120, 150);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 120, 150);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.HP, Ability.Details.Hit_Data.Target.ST);
				}
				break;
			case 8641:
				if(!ex) {
					ex = true;
					ret.getDetails().getHits().clear();
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 20, 12, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.HP, Ability.Details.Hit_Data.Target.ST);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 30, 18, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.HP, Ability.Details.Hit_Data.Target.ST);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 50, 30, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 50, 30, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 50, 30, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 50, 30, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 50, 30, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.BRV, Ability.Details.Hit_Data.Target.ST, 50, 30, 180);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, Ability.Details.Hit_Data.Type.HP, Ability.Details.Hit_Data.Target.ST);
				}
				break;
			case 3929:
				ret.getAilmentById(916).setTarget(Ailment.Target.Party);
				ret.fixMissingAuraAilment(916, 133, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(916, 173, Ailment.EffectType.E67, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
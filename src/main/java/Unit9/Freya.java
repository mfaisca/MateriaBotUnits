package Unit9;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Target;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Type;
import com.materiabot.GameElements.Ailment.EffectGrouping;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Freya")
public class Freya extends Unit{
	public Freya() {
		super("Freya");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7977};
				break;
			case HP:
				ids = new int[]{7981};
				break;
			case S1:
				ids = new int[]{7973};
				break;
			case S2:
				ids = new int[]{7979};
				break;
			case EX:
				ids = new int[]{7984};
				break;
			case AA:
				ids = new int[]{5767};
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
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 7973: //S1
				if(ret.getDetails().getHits().stream().noneMatch(h -> h.getType().equals(Ability.Details.Hit_Data.AttackName.BRV))) {
					ret.getDetails().setChaseDmg(3);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, AttackName.BRV, Target.ST, 100, 120);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, AttackName.BRV, Target.ST, 100, 120);
					ret.addHits(Ability.Details.Hit_Data.Attack_Type.Ranged, AttackName.HP, Target.ST);
					ret.getAilmentById(862).getAuras().get(122).ailmentEffect = Ailment.EffectType.E7.getId();
					ret.getAilmentById(862).getAuras().get(122).target = Ailment.Target.Party.getId();
				}break;
			case 7979: //S2
				ret.getAilmentById(865).setRank(-1);
				if(ret.getAilmentById(865).getEffects().get(0).fakeDesc == null) {
					ret.getAilmentById(865).getEffects().add(0, new EffectGrouping("+1 stack to self if target is broken"));
					ret.getDetails().getAilments().remove(1);
				}
				break;
			case 5767: //AA
				ret.getAilmentById(1162).getAuras().get(324).target = Ailment.Target.Party.getId();
				break;
			case 7984: //EX
				ret.getAilmentById(1555).getAuras().get(608).ailmentEffect = Ailment.EffectType.E1.getId();
				ret.getAilmentById(1555).getAuras().get(608).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1555).getAuras().get(609).ailmentEffect = Ailment.EffectType.E67.getId();
				ret.getAilmentById(1555).getAuras().get(609).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1555).getAuras().get(610).ailmentEffect = Ailment.EffectType.E7.getId();
				ret.getAilmentById(1555).getAuras().get(610).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1555).getAuras().get(611).target = Ailment.Target.Party.getId();
				break;
		}
		return ret;
	}
}
package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Nine")
public class Nine extends Unit{
	public Nine() {
		super("Nine");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9375};
				break;
			case HP:
				ids = new int[]{9376};
				break;
			case S1:
				ids = new int[]{7609};
				break;
			case S2:
				ids = new int[]{7617};
				break;
			case EX:
				ids = new int[]{9374};
				break;
			case AA:
				ids = new int[]{7627};
				break;
			case LD:
				switch(region) {
					case "JP":
						ids = new int[]{12564};break;
					case "GL":
						ids = new int[]{9377};break;
				}
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
		if(ret == null) return null;
		switch(ret.getId()) {
			case 7609: //S1
			case 7617: //S2
				Ailment a = ret.getAilmentById(1459);
				ret.getDetails().getAilments().remove(a);
				if(ret.getAilmentById(1459) != null)
					ret.getDetails().getAilments().remove(ret.getAilmentById(1459));
				ret.getDetails().getAilments().add(a);
				a.setRank(-1);
				ret.addStaticAilmentEffect(1459, "+1 stack to self when used against 2+ enemies", 0);
				break;
			case 9374: //EX
				ret.addStaticAilmentEffect(1468, "Potencies double when against 2+ enemies", 0);
				break;
			case 12564:
				if(ret.getAilmentById(2643) != null) {
					ret.fixMissingAuraAilment(2643, 1176, Ailment.EffectType.E67, Ailment.Target.Party);
					ret.fixMissingAuraAilment(2643, 1175, null, Ailment.Target.Party);
					ret.fixMissingAuraAilment(2643, 1167, Ailment.EffectType.E58, Ailment.Target.Party);
					ret.getAilmentById(1459).getEffects().addAll(ret.getAilmentById(2643).getEffects());
					ret.getAilmentById(1459).getAuras().putAll(ret.getAilmentById(2643).getAuras());
					ret.removeAilmentById(2643);
				}
				ret.getAilmentById(1459).setRank(-1);
//				ret.getAilmentById(1712).getEffects().get(2).rankData = Arrays.asList("030040100").toArray(new String[0]);
//				ret.getAilmentById(1712).getEffects().get(4).rankData = Arrays.asList("030040100").toArray(new String[0]);
				ret.getAilmentById(1712).getEffects().get(2).rankData = Arrays.asList("000", "030", "000").toArray(new String[0]);
				ret.getAilmentById(1712).getEffects().get(3).rankData = Arrays.asList("000", "040", "000").toArray(new String[0]);
				ret.getAilmentById(1712).getEffects().get(4).rankData = Arrays.asList("000", "100", "000").toArray(new String[0]);
			case 9377: //LD
				if(ret.getAilmentById(1712) == null)
					ret.getDetails().getAilments().add(this.getSpecificAilment(1712));
				ret.getHitDataById(10054).setArguments(new Integer[] {1712});
				ret.addStaticAilmentEffect(1701, "After taking an HP hit, transforms to ?**" + ret.getAilmentById(1712).getName() + "**」");
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			
		}
		return ret;
	}
}
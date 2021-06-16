package Unit14;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Lyse")
public class Lyse extends Unit{
	public Lyse() {
		super("Lyse");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5722, 5289};
				break;
			case HP:
				ids = new int[]{5286};
				break;
			case S1:
				ids = new int[]{5276};
				break;
			case S2:
				ids = new int[]{5280, 5284};
				break;
			case EX:
				ids = new int[]{5782, 5783}; //5657, 5658
				break;
			case AA:
				ids = new int[]{5630};
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
	
	private boolean expM = true;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		Ailment ailCha = this.getSpecificAilment(1089);
		ailCha.setName("Chakra");
		switch(ret.getId()) {
			case 5782: //EX
				ret.getAilmentById(1090).removeEffect(141, 60);
				if(!ret.getDetails().getAilments().contains(ailCha)) {
					ret.getDetails().getAilments().add(ailCha);
					ret.getDetails().getHits().addAll(this.getSpecificAbility(5657).getDetails().getHits());
				}
				break;
			case 5783: //EX+
				ret.addStaticHit("Enabled when ?**" + ailCha.getName() + "**」 has 5 stacks", 0);
				ret.getAilmentById(1090).removeEffect(141, 60);
				if(expM) {
					expM = false;
					ret.getDetails().getHits().addAll(this.getSpecificAbility(5658).getDetails().getHits());
				}
				break;
			case 5289: //BRV++
				ret.addStaticHit("Enabled when ?**" + ailCha.getName() + "**」 has 5 stacks", 0);
				break;
			case 5280: //S2
				ret.fixMissingAuraAilment(1088, 278, Ailment.EffectType.E4, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1088, 279, Ailment.EffectType.E8, Ailment.Target.Party);
				break;
			case 5284: //S2+
				ret.addStaticHit("Enabled when ?**" + getSpecificAilment(1088).getName() + "**」 is active", 0);
				break;
		}
		return ret;
	}
}
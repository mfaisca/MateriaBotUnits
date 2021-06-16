package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Celes")
public class Celes extends Unit{
	public Celes() {
		super("Celes");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{1299};
				break;
			case HP:
				ids = new int[]{5636};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{5633}; break;
					case "JP":
						ids = new int[]{11631}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{5635}; break;
					case "JP":
						ids = new int[]{11633}; break;
				}
				break;
			case EX:
				ids = new int[]{5640};
				break;
			case AA:
				ids = new int[]{5644};
				break;
			case LD:
				ids = new int[]{11598, 11634};
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
			case 5635: //S2
				ret.getAilmentById(664).setRank(-1);
				Ailment.Aura a = ret.fixMissingAuraAilment(664, 314, null, Ailment.Target.AoE);
				for(int i = 0; i < a.rankData.length; i++)
					a.rankData[i] = (a.rankData[i].contains("-") ? "" : "-") + a.rankData[i];
				break;
			case 5640: //EX
				ret.getAilmentById(667).removeEffect(60);
				break;
			case 5644: //AA
				ret.fixMissingAuraAilment(1148, 313, null, Ailment.Target.Party);
				break;
			case 11598:
				ret.removeHitDataById(12038);
				ret.removeHitDataById(12038);
				ret.fixDelayHitData(11973);
				break;
			case 11634:
				ret.getDetails().setMovementCost(30);
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			case 2462:
				ret.setName(this.getSpecificAilment(223).getName());
				break;
		}
		return ret;
	}
}
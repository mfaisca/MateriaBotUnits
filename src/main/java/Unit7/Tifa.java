package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Tifa")
public class Tifa extends Unit{
	public Tifa() {
		super("Tifa");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6003, 12677};
				break;
			case HP:
				ids = new int[]{6004, 12678};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{5786, 6002}; break;
					case "JP":
						ids = new int[]{12668, 12669}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{5788}; break;
					case "JP":
						ids = new int[]{12671}; break;
				}
				break;
			case EX:
				ids = new int[]{5792};
				break;
			case AA:
				ids = new int[]{5797};
				break;
			case LD:
				ids = new int[]{12676, 12677, 12678};
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
			case 6002: //S1+
				ret.removeHitDataById(5333);
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled after breaking or hitting a broken target with an ability");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				break;
			case 5788: //S2
				Ailment ail = ret.getAilmentById(289);
				String[] rankData = ail.getEffects().get(1).rankData;
				rankData[6] = rankData[5];
				break;
			case 5792: //EX
				String name = ret.getName();
				Ability skill2 = this.getSpecificAbility(5793);
				skill2.getDetails().getHits().addAll(0, ret.getDetails().getHits());
				ret.getDetails().getHits().clear();
				ret = skill2;
				ret.fixDelayHitData(5126);
				ret.setName(name);
				break;
			case 12671: //S2 JP
				ret.fixMergeAbility(12673);
				ret.getDetails().setChaseDmg(3);
				Ailment ail2 = ret.getAilmentById(289);
				String[] rankData2 = ail2.getEffects().get(1).rankData;
				rankData2[6] = rankData2[5];
				break;
			case 12676: //LD
				ret.addStaticHit("Enables ?**" + super.getSpecificAbility(12677).getName() + "**」 for 1 turn");
				ret.addStaticHit("Enables ?**" + super.getSpecificAbility(12678).getName() + "**」 for 1 turn");
				break;
			case 12677:
			case 12678:
				ret.addStaticHit("Enabled after using ?**" + this.getSpecificAbility(12676).getName() + "**」 for 1 use __**(shared)**__", 0);
				ret.fixRemoveDispels();
				break;
		}
		return ret;
	}
}
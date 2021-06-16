package Unit14;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Yshtola")
public class Yshtola extends Unit{
	public Yshtola() {
		super("Y'shtola", "yshtola", "ysh");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{3489}; break;
					case "JP":
						ids = new int[]{11804, 11805}; break;
				}
				break;
			case HP:
				ids = new int[]{5622};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{3459, 3461}; break;
					case "JP":
						ids = new int[]{11798}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{3463}; break;
					case "JP":
						ids = new int[]{11800}; break;
				}
				break;
			case EX:
				ids = new int[]{5621};
				break;
			case AA:
				ids = new int[]{5626};
				break;
			case LD:
				ids = new int[]{11803};
				break;
			case BT:
				ids = new int[]{11657};
				break;
			case CA:
				ids = new int[]{};
				break;
		}
		if(ids.length == 0)
			return super.getAbility(type, region);
		else
			return IntStream.of(ids).boxed().map(i -> this.getSpecificAbility(i, region)).collect(Collectors.toList());
	}
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		switch(ret.getId()) {
			case 3461:
				ret.addStaticHit("Enabled if BRV >= 30% Max BRV", 0);
			case 11798:
				ret.fixDelayHitData(3916); break;
			case 5626:
				ret.getAilmentById(1146).setRank(0);
				ret.fixMissingAuraAilment(1146, 311, Ailment.EffectType.E5, Ailment.Target.Party); break;
			case 11805:
				ret.addStaticHit("Enabled when ?**" + super.getSpecificAilment(2464).getName() + "**」 has 3 stacks", 0);
				break;
			case 11657:
				ret.fixMissingAuraAilment(2465, 1032, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2465, 1033, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2465, 1034, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2465, 1035, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
	
	private Ability getSpecificAbility(int id, String region){
		Ability ret = this.getSpecificAbility(id);
		//This issue for HP++, AA and EX because they give stacks in the old skill version, so this function serves to remove it for GL and keep it for JP
		if(ret.getId() == 5622 || ret.getId() == 5621 || ret.getId() == 5626) {
			ret.removeHitDataById(12169);
			switch(region) {
				case "JP":
					ret.addStaticHit("Increases ?**ブラッドリリー**」 stacks by 1");
					ret.getDetails().getHits().get(ret.getDetails().getHits().size()-1).setId(12169);
					break;
				case "GL":
					break;
			}
		}
		return ret;
	}
}
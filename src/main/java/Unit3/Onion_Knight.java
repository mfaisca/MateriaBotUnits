package Unit3;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Onion_Knight")
public class Onion_Knight extends Unit{
	public Onion_Knight() {
		super("Onion Knight", "ok", "onion");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{2149, 3530, 3532};
				break;
			case HP:
				ids = new int[]{6637, 6636};
				break;
			case S1:
				ids = new int[]{3519};
				break;
			case S2:
				ids = new int[]{3527};
				break;
			case EX:
				ids = new int[]{6635, 6634};
				break;
			case AA:
				ids = new int[]{6649};
				break;
			case LD:
				ids = new int[]{10619};
				break;
			case BT:
				ids = new int[]{10616, 10617};
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
			case 6635:
				ret.fixMissingAuraAilment(1281, 405, Ailment.EffectType.E67, Ailment.Target.Party);
			case 3519:
				Ailment ailS = ret.getAilmentById(89);
				ailS.setRank(-1);
				for(Ailment.EffectGrouping eg : ailS.getEffects())
					if(eg.rankData != null)
						for(int i = 0; i < eg.rankData.length; i++)
							if(eg.rankData[i].length() == 5)
									eg.rankData[i] += eg.rankData[i].substring(2);
				ret.addStaticAilmentEffect(89, "+40% party ATK (3 stacks only)");
				break;
			case 6634:
				ret.fixMissingAuraAilment(1281, 405, Ailment.EffectType.E67, Ailment.Target.Party);
			case 3527:
				Ailment ailN = ret.getAilmentById(88);
				ailN.setRank(-1);
				for(Ailment.EffectGrouping eg : ailN.getEffects())
					if(eg.rankData != null)
						for(int i = 0; i < eg.rankData.length; i++)
							if(eg.rankData[i].length() == 5)
								eg.rankData[i] += eg.rankData[i].substring(2);
				ret.addStaticAilmentEffect(88, "+40% party ATK (3 stacks only)");
				break;
			case 6649:
				ret.fixMissingAuraAilment(1283, 406, null, Ailment.Target.Party);
				break;
			case 10619:
				ret.fixMergeAbility(11403);
				ret.fixMissingAuraAilment(2008, 924, null, Ailment.Target.Party);
				break;
			case 10616:
				ret.fixMissingAuraAilment(2029, 916, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2029, 917, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2029, 918, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2029, 919, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2029, 1002, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2029, 1006, null, Ailment.Target.Party);
				break;
			case 10617:
				ret.fixMissingAuraAilment(2030, 920, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2030, 921, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2030, 922, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2030, 923, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2030, 1003, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2030, 1006, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
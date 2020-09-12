package Unit7;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ailment.EffectGrouping;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Sephiroth")
public class Sephiroth extends Unit{
	public Sephiroth() {
		super("Sephiroth", "seph");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10204};
				break;
			case HP:
				ids = new int[]{10205};
				break;
			case S1:
				ids = new int[]{3668};
				break;
			case S2:
				ids = new int[]{3674};
				break;
			case EX:
				ids = new int[]{6193};
				break;
			case AA:
				ids = new int[]{6167};
				break;
			case LD:
				ids = new int[]{10009};
				break;
			case BT:
				ids = new int[]{10010};
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
			case 3668:{ //S1
				ret.removeHitDataById(4301);
				}break;
			case 3674: //S2
				Ailment ailJ = ret.getAilmentById(341);
				if(!ailJ.getName().equals("Jenova")) {
					ailJ.setName("Jenova");
					ailJ.setRank(-1);
					EffectGrouping egA = new EffectGrouping(Ailment.EffectType.E1.getId());
					Ailment aA = ret.getAilmentById(136);
					egA.rankData = new String[]{"0", aA.getEffects().get(0).rankData[0], aA.getEffects().get(0).rankData[1], 
													aA.getEffects().get(0).rankData[2], aA.getEffects().get(0).rankData[3]};
					EffectGrouping egM = new EffectGrouping(Ailment.EffectType.E5.getId());
					Ailment aM = ret.getAilmentById(152);
					egM.rankData = new String[]{aM.getEffects().get(0).rankData[0], aM.getEffects().get(0).rankData[1], 
												 aM.getEffects().get(0).rankData[2], aM.getEffects().get(0).rankData[3], aM.getEffects().get(0).rankData[4]};
					ailJ.getEffects().add(egA);
					ailJ.getEffects().add(egM);
					ailJ.getEffects().get(1).effectId = Ailment.EffectType.E60.getId();
					ailJ.getAuras().get(2).ailmentEffect = Ailment.EffectType.E3.getId();
					ailJ.getAuras().get(2).target = Ailment.Target.AoE.getId();
					ailJ.getAuras().get(2).rankData = new String[] {"-10"};
					ret.removeAilmentById(136);
					ret.removeAilmentById(152);
					ret.getDetails().getAilments().remove(1); //Remove second Jenova buff
				}break;
			case 6193:{ //EX
				ret.getAilmentById(829).setRank(-1);
				ret.getDetails().getHits().sort((h1, h2) -> h1.getId() - h2.getId());
				Ability.Details.Hit_Data hd = ret.getHitDataById(3898);
				ret.getDetails().getHits().remove(hd);
				ret.getDetails().getHits().add(hd);
				}break;
			case 10009: //LD
				//ret.getAilmentById(1841).getEffects().get(1).rankData[0] = "10-30000";
				break;
			case 10010:{ //BT
				if(ret.getAilmentById(1840).getEffects().size() == 3) {
					ret.getAilmentById(1840).removeEffect(258, 118);
					ret.getAilmentById(1840).getAuras().get(824).target = Ailment.Target.AoE.getId();
				}
				break;}
		}
		return ret;
	}
}
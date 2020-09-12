package Unit9;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Ability.Details.Hit_Data.EffectType;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Target;
import com.materiabot.GameElements.Ability.Details.Hit_Data.Type;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Beatrix")
public class Beatrix extends Unit{
	public Beatrix() {
		super("Beatrix", "bea", "bae", "baetrix");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7477, 7482};
				break;
			case HP:
				ids = new int[]{7262, 7483};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{7260}; 
						break;
					case "JP":
						ids = new int[]{10496}; 
						break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{7265, 7266};
						break;
					case "JP":
						ids = new int[]{10500, 10501};
						break;
				}
				break;
			case EX:
				ids = new int[]{7274, 7481, 7275}; //Last = Paladin Protection
				break;
			case AA:
				ids = new int[]{7283};
				break;
			case LD:
				ids = new int[]{10361, 10362};
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
		int c = 0, r = 0, o = 0;
		switch(ret.getId()) {
			case 10496: //S1 //JP
				c = 3; r = 60; o = 150;
				ret.setType(Ability.Type.S1);
				if(ret.getUseCount() == 6)
					ret.setUseCount(7);
			case 7260:  //S1 //GL
				c = c == 0 ? 2 : c; r = r == 0 ? 80 : r; o = o == 0 ? 120 : o;
				if(ret.getDetails().getHits().size() == 1) {
					Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data();
					adh.setAttackType(Ability.Details.Hit_Data.Attack_Type.Magic);
					adh.setBrvRate(r);
					adh.setEffect(new Ability.Details.Hit_Data.Effect(EffectType.E1, 0));
					adh.setMaxBrvOverflow(o);
					adh.setTarget(Target.ST);
					adh.setType(Type.BRV);
					for(int i = 0; i < c; i++)
						ret.getDetails().getHits().add(adh);
					adh = adh.clone();
					adh.setType(Type.HP);
					ret.getDetails().getHits().add(adh);
				}break;
			case 10501: //S2+ JP
			case 7266:{ //S2+ GL
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when **total party buffs** >= 9");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				} break;
			case 7482: //BRV+++
			case 7483: //HP+++
				c = 12;
			case 7477: //BRV++
			case 7262:{ //HP++
				c = c == 0 ? 9 : c;
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when **total party buffs** >= " + c);
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				} break;
			case 7481: //EX++
				c = 12;
			case 7274:{ //EX+
				c = c == 0 ? 9 : c;
				ret.fixMissingAuraAilment(1401, 466, null, Ailment.Target.Party);
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when **total party buffs** >= " + c);
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
//				adh = new Ability.Details.Hit_Data("Enables 「**Holy Knight's Safeguard**」 for 1 use");
//				if(!ret.getDetails().getHits().contains(adh))
//					ret.getDetails().getHits().add(adh);
				}break;
			case 7275: //EX(HP++++)
				ret.removeHitDataById(504);
				ret.getAilmentById(1401).getAuras().get(466).rankData[0] = ret.getAilmentById(1401).getAuras().get(466).rankData[0].replace("-", "");
				ret.fixMissingAuraAilment(1401, 466, null, Ailment.Target.Party);
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled after using 「**Climhazzard**」 for 1 use");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				break;
			case 7283: //AA
				ret.getAilmentById(1418).getAuras().get(483).ailmentEffect = Ailment.EffectType.E1.getId();
				ret.getAilmentById(1418).getAuras().get(483).target = Ailment.Target.Party.getId();
				break;
			case 10361:
				ret.addStaticAilmentEffect(1126, "Trigger 「**" + super.getSpecificAbility(1951).getName() + "**」 when attacked");
				break;
		}
		return ret;
	}
}
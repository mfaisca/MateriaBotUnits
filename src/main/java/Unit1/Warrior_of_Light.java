package Unit1;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Warrior_of_Light")
public class Warrior_of_Light extends Unit{
	public Warrior_of_Light() {
		super("Warrior of Light", "wol", "swole");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10025, 10026}; break;
			case HP:
				ids = new int[]{10027, 10028}; break;
			case S1:
				ids = new int[]{10024}; break;
			case S2:
				ids = new int[]{10030}; break;
			case EX:
				ids = new int[]{5124};
				break;
			case AA:
				ids = new int[]{3925};
				break;
			case LD:
				switch(region) {
					case "GL":
						ids = new int[]{9383}; break;
					case "JP":
						ids = new int[] {12988}; break;
				}
				break;
			case BT:
				ids = new int[]{9384};
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
			case 10025: //BRV+ JP
			case 10027: //HP+ JP
				ret.addStaticHit("Enabled when 1+ 「**" + super.getSpecificAilment(330).getName() + "**」 is active on party", 0);
				break;
			case 10026: //BRV+ JP
			case 10028: //HP+ JP
				ret.addStaticHit("Enabled when 3 「**" + super.getSpecificAilment(330).getName() + "**」 are active on party", 0);
				break;
			case 10024: //S1 JP
			case 10030: //S2 JP
				ret.fixMissingAuraAilment(950, 153, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(950, 154, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(950, 155, Ailment.EffectType.E8, Ailment.Target.Party);
				break;
			case 3925: //AA Missing Aura Effect(60)
				Ailment a = ret.getAilmentById(931);
				if(a.getEffects().size() == 0)
					a.getEffects().add(new Ailment.EffectGrouping(Ailment.EffectType.E60.getId()));
				break;
			case 9383:{ //LD
				ret.removeHitDataById(10255);
				Ailment ld = ret.getAilmentById(1746);
				if(!ld.getName().equals("Guardian Shield")) {
					ret.fixMissingAuraAilment(1746, 759, null, Ailment.Target.Party);
					//ld.getAuras().remove(759);
					ld.setName("Guardian Shield");
					Ailment.Aura a814 = ld.getAuras().get(814);
					Ailment.Aura a815 = ld.getAuras().get(815);
					Ailment.Aura a816 = new Ailment.Aura(); 
					a816.id = 816;
					a816.rankData = new String[] {Shared.Methods.splitRankData(a814.rankData[0])[1]};
					Ailment.Aura a817 = new Ailment.Aura();
					a817.id = 817;
					a817.rankData = new String[] {Shared.Methods.splitRankData(a815.rankData[0])[1]};
					ld.getAuras().put(816, a816);
					ld.getAuras().put(817, a817);
					ret.fixMissingAuraAilment(1746, 814, Ailment.EffectType.E1, Ailment.Target.Party);
					ret.fixMissingAuraAilment(1746, 815, Ailment.EffectType.E5, Ailment.Target.Party);
					ret.fixMissingAuraAilment(1746, 816, Ailment.EffectType.E2, Ailment.Target.Party);
					ret.fixMissingAuraAilment(1746, 817, Ailment.EffectType.E4, Ailment.Target.Party);
				}
			}	break;
			case 12988:
				ret.removeHitDataById(10255);
				//TODO
				break;
			case 9384: //BT
				ret.fixMissingAuraAilment(1745, 758, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1745, 843, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1745, 844, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
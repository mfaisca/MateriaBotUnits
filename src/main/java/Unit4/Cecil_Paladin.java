package Unit4;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cecil_Paladin")
public class Cecil_Paladin extends Unit{
	public Cecil_Paladin() {
		super("Cecil Paladin", "pecil", "pencil", "paladin cecil");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7117};
				break;
			case HP:
				ids = new int[]{7119};
				break;
			case S1:
				ids = new int[]{7024};
				break;
			case S2:
				ids = new int[]{7026};
				break;
			case EX:
				ids = new int[]{7040};
				break;
			case AA:
				ids = new int[]{7038};
				break;
			case LD:
				ids = new int[]{11252};
				break;
			case BT:
				ids = new int[]{11277};
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
			case 7038: //AA
				ret.fixMissingAuraAilment(1374, 464, Ailment.EffectType.E1, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1374, 463, null, Ailment.Target.Party);
				break;
			case 11252: //LD
				if(ret.getAilmentById(2131) != null) {
					ret.addStaticHit(ret.getAilmentById(2131).generateDescription().split(System.lineSeparator())[1], 0);
					ret.removeAilmentById(2131);
					ret.fixRemoveDispels();
				}
//				ret.fixMissingAuraAilment(2083, 976, null, Ailment.Target.Party);
//				ret.fixMissingAuraAilment(2083, 977, null, Ailment.Target.Party);
//				ret.fixMissingAuraAilment(2083, 1023, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2083, 978, Ailment.EffectType.E2, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2083, 975, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2083, 974, null, Ailment.Target.Party);
				if(ret.getAilmentById(2083).getAuras().get(976) != null) {
					ret.getAilmentById(2083).getAuras().remove(976);
					ret.getAilmentById(2083).getAuras().remove(977);
					ret.getAilmentById(2083).getAuras().remove(1023);
					ret.addStaticAilmentEffect(2083, "If own HP >= 100%");
					ret.addStaticAilmentEffect(2083, "+50% party BRV Damage dealt");
					ret.addStaticAilmentEffect(2083, "+20% party HP Damage dealt");
					ret.addStaticAilmentEffect(2083, "-20% party HP Damage taken");
				}
				break;
			case 11277: //BT
				ret.fixMissingAuraAilment(2046, 1008, null, Ailment.Target.Party);
//				ret.fixMissingAuraAilment(2046, 1009, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2046, 1007, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2046, 942, null, Ailment.Target.Party);
//				ret.fixMissingAuraAilment(2046, 1010, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2046, 1011, Ailment.EffectType.E7, Ailment.Target.Party);
				ret.getAilmentById(2046).getAuras().remove(1009);
				ret.getAilmentById(2046).getAuras().remove(1010);
				ret.addStaticAilmentEffect(2046, "If own HP >= 100%");
				ret.addStaticAilmentEffect(2046, "+20% party Maximum BRV damage limit");
				ret.addStaticAilmentEffect(2046, "+10% party Maximum obtainable BRV & HP damage limit");
				break;
		}
		return ret;
	}
}
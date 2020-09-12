package Unit14;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Alphinaud")
public class Alphinaud extends Unit{
	public Alphinaud() {
		super("Alphinaud", "alphi", "alibro");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4646, 8279};
				break;
			case HP:
				ids = new int[]{4650, 8280};
				break;
			case S1:
				ids = new int[]{8381};
				break;
			case S2:
				ids = new int[]{4643};
				break;
			case EX:
				ids = new int[]{8424};
				break;
			case AA:
				ids = new int[]{4654};
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
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		ret.removeHitDataById(5584);
		this.getSpecificAilment(939).setName("Moonstone Carbuncle");
		switch(ret.getId()) {
			case 8381: //S1
				Ailment ailMS = this.getSpecificAilment(531);
				Ailment ailMC = this.getSpecificAilment(939);
				if(!ret.getDetails().getAilments().contains(ailMS)) {
					ret.getDetails().getAilments().add(ailMS);
					ret.getDetails().getAilments().remove(ailMC);
					//ret.getDetails().getAilments().add(new Ailment(Ailment.Emotes.BUFF_INVISIBLE.get(), "Filler", "「**" + super.getSpecificAilment(939).getName() + "**」" + System.lineSeparator() + "is now readable"));
					ret.getDetails().getAilments().add(ailMC);
				}
				ret.fixMissingAuraAilment(939, 158, Ailment.EffectType.E5, Ailment.Target.Party);
				ret.fixMissingAuraAilment(939, 159, Ailment.EffectType.E63, Ailment.Target.Party);
				ailMC.removeEffect(264, 249, 344);
				ret.addStaticAilmentEffect(ailMC.getId(), "If **HP < 30% Max HP**       -> __HP Regen (20% Max HP)__");
				ret.addStaticAilmentEffect(ailMC.getId(), "If **Cur BRV < Int BRV**     -> __BRV Regen (100% IBRV)__");
				ret.addStaticAilmentEffect(ailMC.getId(), "If **Cur BRV > Int BRV &** ");
				ret.addStaticAilmentEffect(ailMC.getId(), "  **Cur BRV < 80% Max BRV**  -> __Party BRV Regen (50% IBRV)__");
				ret.addStaticAilmentEffect(ailMC.getId(), "If **Cur BRV > 80% Max BRV** -> __Grant 「**" + super.getSpecificAilment(531).getName() + "**」__");
				break;
			case 4654: //AA
				ret.fixMissingAuraAilment(1009, 217, Ailment.EffectType.E8, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1009, 218, Ailment.EffectType.E4, Ailment.Target.Party);
				break;
			case 8424: //EX
				Ailment ailOS = this.getSpecificAilment(1545);
				ret.fixMissingAuraAilment(1543, 601, Ailment.EffectType.E3, Ailment.Target.AoE);
				ret.addStaticAilmentEffect(1474, "Grant 「**" + super.getSpecificAilment(1545).getName() + "**」 at start of turn");
				if(!ret.getDetails().getAilments().contains(ailOS))
					ret.getDetails().getAilments().add(ailOS);
				ailOS.getEffects().get(1).effectId = Ailment.EffectType.E65.getId();
				ailOS.getEffects().get(1).rankData = new String[] {"20020", "20020"};
				this.getSpecificAilment(1474).removeEffect(264, 249, 344);
				break;
		}
		return ret;
	}
}
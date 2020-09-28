package Unit6;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Gau")
public class Gau extends Unit{
	public Gau() {
		super("Gau");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6806};
				break;
			case HP:
				ids = new int[]{6807};
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{5478, 6555, 6556, 6764}; break;
					case "JP":
						ids = new int[]{11270, 6555, 6556, 6764}; break; //11271 - There was a new counter, but it wasn't on the rework?
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{5484, 6559, 6562, 6765}; break;
					case "JP":
						ids = new int[]{11273, 6559, 6562, 6765}; break; //11274 - There was a new counter, but it wasn't on the rework?
				}
				break;
			case EX:
				ids = new int[]{6567};
				break;
			case AA:
				ids = new int[]{6571};
				break;
			case LD:
				ids = new int[]{10055, 10176, 10177, 11276};
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
		this.getSpecificAilment(1332).setFake("Lifeshaver Stance", null, Ailment.Emotes.BUFF_INVISIBLE.get());
		this.getSpecificAilment(1333).setFake("Cat Scratch Stance", null, Ailment.Emotes.BUFF_INVISIBLE.get());
		this.getSpecificAilment(1922).setFake("Landslide Stance", null, Ailment.Emotes.BUFF_INVISIBLE.get());
		switch(ret.getId()) {
			case 5478: //S1
				ret.addStaticHit("Disabled when 「**" + super.getSpecificAilment(1333).getName() + "**」 is active", 0);
				ret.removeAilmentById(1130);
				ret.fixRemoveDispels();
				ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1332);
				ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1922);
				ret.removeAilmentById(1332);
				ret.getDetails().getAilments().add(this.getSpecificAilment(1332));
				break;
			case 6555: //S1 BRV
			case 6556: //S1 HP
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(1332).getName() + "**」 is active", 0);
				break;
			case 6764: //S1 Counter
				ret.setName("Lifeshaver Counter");
				break;
			case 5484: //S2
				ret.addStaticHit("Disabled when 「**" + super.getSpecificAilment(1332).getName() + "**」 is active", 0);
				ret.removeAilmentById(1131);
				ret.fixRemoveDispels();
				ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1333);
				ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1922);
				ret.removeAilmentById(1333);
				ret.getDetails().getAilments().add(this.getSpecificAilment(1333));
				break;
			case 6559: //S2 BRV
			case 6562: //S2 HP
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(1333).getName() + "**」 is active", 0);
				break;
			case 6765: //S2 Counter
				ret.setName("Cat Scratch Counter");
				break;
			case 10055: //LD
				ret.fixRemoveDispels();
				ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1332);
				ret.addEffectHit(Ability.Details.Hit_Data.EffectType.E37, Ability.Details.Hit_Data.Target.Self, 0, 1333);
				ret.removeAilmentById(1922);
				ret.getDetails().getAilments().add(this.getSpecificAilment(1922));
				ret.fixMissingAuraAilment(1897, 849, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1897, 850, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1897, 851, null, Ailment.Target.AoE);
				ret.fixMissingAuraAilment(1897, 852, null, Ailment.Target.AoE);
				break;
			case 10176: //LD BRV
			case 10177: //LD HP
				ret.addStaticHit("Enabled when 「**" + super.getSpecificAilment(1922).getName() + "**」 is active", 0);
				break;
			case 11276: //LD Counter
				ret.setName("Landslide Counter");
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(ret.getId()) {
			case 1332:
				ret.setArgs(new int[] {6764});
				ret.getEffects().stream().filter(eg -> eg.effectId == 69).findFirst().get().val_specify = 6764;
				break;
			case 1333:
				ret.setArgs(new int[] {6765});
				ret.getEffects().stream().filter(eg -> eg.effectId == 69).findFirst().get().val_specify = 6765;
				break;
			case 1922:
				ret.setArgs(new int[] {11276});
				ret.getEffects().stream().filter(eg -> eg.effectId == 69).findFirst().get().val_specify = 11276;
				break;
		}
		return ret;
	}
}
package Unit6;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Terra")
public class Terra extends Unit{
	public Terra() {
		super("Terra", "tina");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{6766};
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{6767}; break;
					case "JP":
						ids = new int[]{11939}; break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{4416, 4444}; break;
					case "JP":
						ids = new int[]{11932, 11933}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{4423, 4446}; break;
					case "JP":
						ids = new int[]{11936, 11937}; break;
				}
				break;
			case EX:
				ids = new int[]{6575, 6769};
				break;
			case AA:
				ids = new int[]{4359};
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
	
	Boolean s1 = null, s2 = null;
	
	@Override
	public Ability getSpecificAbility(int id){
		Ability ret = super.getSpecificAbility(id);
		if(ret.getAilmentById(982) != null)
			ret.getAilmentById(982).setName("Magic");
		switch(ret.getId()) {
			case 4416:
				ret.getHitDataById(5060).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.fixMergeAbility(4417);
				break;
			case 4444:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(982).getName() + "**」 has 5+ stacks", 0);
				ret.getHitDataById(5060).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.fixMergeAbility(4418); break;
			case 11932:
				ret.getHitDataById(5060).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.fixMergeAbility(12188); break;
			case 11933:
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(982).getName() + "**」 has 5+ stacks", 0);
				ret.getHitDataById(5060).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.fixMergeAbility(12189); break;
			case 4423:
				ret.fixRemoveDispels();
				ret.getHitDataById(5069).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.fixMergeAbility(4424); break;
			case 4446:
				ret.fixRemoveDispels();
				ret.getHitDataById(5069).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(982).getName() + "**」 has 5+ stacks", 0);
				ret.fixMergeAbility(4425); break;
			case 11936:
				ret.fixRemoveDispels();
				ret.getHitDataById(5069).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.fixMergeAbility(12192); break;
			case 11937:
				ret.fixRemoveDispels();
				ret.getHitDataById(5069).getEffect().setEffectValueType(Ability.Details.Hit_Data.BasedOnStat.Stat5.getId());
				ret.addStaticHit("Enabled when 「**" + this.getSpecificAilment(982).getName() + "**」 has 5+ stacks", 0);
				ret.fixMergeAbility(12193); break;
			case 6769:
				ret.fixRemoveDispels();
				ret.addStaticHit("Enabled after using 「**" + this.getSpecificAbility(6575).getName() + "**」 for 1 use");
				break;
			case 11863:
				ret.fixMissingAuraAilment(2507, 1088, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2507, 1057, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2507, 1058, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(2507, 1059, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
	
	@Override
	public Ailment getSpecificAilment(int id){
		Ailment ret = super.getSpecificAilment(id);
		switch(id) {
			case 982:
				ret.setName("Magic");
		}
		return ret;
	}
}
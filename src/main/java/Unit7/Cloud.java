package Unit7;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Cloud")
public class Cloud extends Unit{
	public Cloud() {
		super("Cloud", "thunderhead", "exsoldier", "ex-soldier", "cloudia", "not interested");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				switch(region) {
					case "GL":
						ids = new int[]{3591}; break;
					case "JP":
						ids = new int[]{10552}; break;
				}
				break;
			case HP:
				switch(region) {
					case "GL":
						ids = new int[]{3592}; break;
					case "JP":
						ids = new int[]{10551}; break;
				}
				break;
			case S1:
				switch(region) {
					case "GL":
						ids = new int[]{3588, 3625}; break;
					case "JP":
						ids = new int[]{10547, 10548}; break;
				}
				break;
			case S2:
				switch(region) {
					case "GL":
						ids = new int[]{3590}; break;
					case "JP":
						ids = new int[]{10550}; break;
				}
				break;
			case EX:
				ids = new int[]{5075};
				break;
			case AA:
				ids = new int[]{5029};
				break;
			case LD:
				ids = new int[]{10012};
				break;
			case BT:
				ids = new int[]{10013};
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
		if(ret == null) return null;
		switch(ret.getId()) {
			case 10552: //BRV++
			case 10551:{ //HP++
				ret.fixStupidCriticalDamage(1611, 50);
				break;}
			case 3588:
			case 10547:{ //Cross Slash
				Ability.Details.Hit_Data adh = ret.getHitDataById(689);
				ret.getDetails().getHits().remove(adh);
				break;}
			case 3625:
			case 10548:{ //Cross Slash+
				Ability.Details.Hit_Data adh = ret.getHitDataById(689);
				if(ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().remove(adh);
				ret.addStaticHit("Enabled after using 「**Finishing Touch**」 for 1 use", 0);
				break;}
			case 3590:
			case 10550:{ //Finishing Touch
				ret.removeAilmentById(105);
				ret.removeAilmentById(106);
				ret.addStaticHit("Enables 「**Cross Slash+**」 for 1 use");
				break;}
			case 10013:
				ret.fixMissingAuraAilment(1842, 826, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1842, 827, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1842, 828, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1842, 829, null, Ailment.Target.Party);
				break;
		}
		return ret;
	}
}
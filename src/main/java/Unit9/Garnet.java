package Unit9;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Garnet")
public class Garnet extends Unit{
	public Garnet() {
		super("Garnet", "dagger", "sarah");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{8560, 8564};
				break;
			case HP:
				ids = new int[]{8561, 8565};
				break;
			case S1:
				ids = new int[]{8559, 8560, 8561};
				break;
			case S2:
				ids = new int[]{8563, 8564, 8565};
				break;
			case EX:
				ids = new int[]{8568};
				break;
			case AA:
				ids = new int[]{5045};
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
		switch(ret.getId()) {
			case 8560:
			case 8561:{
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when 「**Water Dragon**」 is active");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				}break;
			case 8564:
			case 8565:{
				Ability.Details.Hit_Data adh = new Ability.Details.Hit_Data("Enabled when 「**Thunder Tsar**」 is active");
				if(!ret.getDetails().getHits().contains(adh))
					ret.getDetails().getHits().add(0, adh);
				}break;
			case 8559: //S1
				ret.removeHitDataById(1237);
				break;
			case 8563: //S2
				ret.removeHitDataById(1236);
				break;
			case 8568: //EX
				ret.getAilmentById(1597).getEffects().get(2).rankData = new String[] {"20080080"};
				break;
			case 5045: //AA
				ret.getAilmentById(1060).getAuras().get(262).ailmentEffect = Ailment.EffectType.E8.getId();
				ret.getAilmentById(1060).getAuras().get(262).target = Ailment.Target.Party.getId();
				ret.getAilmentById(1060).getAuras().get(263).ailmentEffect = Ailment.EffectType.E5.getId();
				ret.getAilmentById(1060).getAuras().get(263).target = Ailment.Target.Party.getId();
				break;
		}
		return ret;
	}
}
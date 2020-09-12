package Unit8;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ultimecia")
public class Ultimecia extends Unit{
	public Ultimecia() {
		super("Ultimecia", "ulti");
	}
	
	@Override
	public List<Ability> getAbility(Ability.Type type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{5420};
				break;
			case HP:
				ids = new int[]{5326};
				break;
			case S1:
				ids = new int[]{4837, 4838};
				break;
			case S2:
				ids = new int[]{4847, 4848};
				break;
			case EX:
				ids = new int[]{5325};
				break;
			case AA:
				ids = new int[]{4855};
				break;
			case LD:
				ids = new int[]{8798};
				break;
			case BT:
				ids = new int[]{10615};
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
			case 5420: //BRV
			case 5326:{ //HP
				Ailment ail = ret.getAilmentById(1017);
				if(ail != null)
					ret.getAilmentById(1017).setArgs(new int[] {0});
				}break;
			case 4838: //S1+
			case 4848: //S2+
				ret.addStaticHit("Enabled when 「**" + ret.getAilmentById(1017).getName() + " V**」 is active");
			case 4837: //S1
			case 4847: //S2
			case 8798:{ //LD
				Ailment ail = ret.getAilmentById(1017);
				if(ail != null)
					ret.getAilmentById(1017).setRank(-1);
				}break;
			case 10615: //BT
				ret.removeHitDataById(11392);
				ret.removeHitDataById(11042);
				ret.removeHitDataById(11042);
				break;
		}
		return ret;
	}
}
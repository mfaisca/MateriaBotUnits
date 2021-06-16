package Unit9;
import com.materiabot.GameElements.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Steiner")
public class Steiner extends Unit{
	public Steiner() {
		super("Steiner", "maci", "macilento", "rusty", "sir rustalot");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{7474};
				break;
			case HP:
				ids = new int[]{7258};
				break;
			case S1:
				ids = new int[]{7249};
				break;
			case S2:
				ids = new int[]{7510};
				break;
			case EX:
				ids = new int[]{7257};
				break;
			case AA:
				ids = new int[]{7279};
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
		Ability ret = super.getSpecificAbility(id); //468
		switch(ret.getId()) {
			case 7279: //AA
				ret.getAilmentById(1419).getAuras().get(485).ailmentEffect = Ailment.EffectType.E5.getId();
				ret.getAilmentById(1419).getAuras().get(485).target = Ailment.Target.Party.getId();
				break;
			case 7257:
				ret.getAilmentById(1402).getAuras().get(468).ailmentEffect = Ailment.EffectType.E5.getId();
				ret.getAilmentById(1402).getAuras().get(468).target = Ailment.Target.Party.getId();
		}
		return ret;
	}
}
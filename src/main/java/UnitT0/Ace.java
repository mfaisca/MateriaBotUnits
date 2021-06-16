package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Enumerators.Ability.AttackName;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Ace")
public class Ace extends Unit{
	public Ace() {
		super("Ace", "acey");
	}
	
	@Override
	public List<Ability> getAbility(AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{4894};
				break;
			case HP:
				ids = new int[]{4895};
				break;
			case S1:
				ids = new int[]{4723, 4724};
				break;
			case S2:
				ids = new int[]{4730, 4731};
				break;
			case EX:
				ids = new int[]{5207};
				break;
			case AA:
				ids = new int[]{4693};
				break;
			case LD:
				ids = new int[]{13987};
				break;
			case BT:
				ids = new int[]{13992};
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
		case 13992:
			for(HitData h : ret.getHitData())
				if(h.getId() == 13934)
					h.setMaxBrvLimitUp(100);
			break;
		}
		return ret;
	}
}
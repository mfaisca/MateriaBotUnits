package UnitT0;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Kurasame")
public class Kurasame extends Unit{
	public Kurasame() {
		super("Kurasame", "kura");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{10035};
				break;
			case HP:
				ids = new int[]{10036};
				break;
			case S1:
				ids = new int[]{9590};
				break;
			case S2:
				ids = new int[]{9594};
				break;
			case EX:
				ids = new int[]{9600};
				break;
			case AA:
				ids = new int[]{9976};
				break;
			case LD:
				ids = new int[]{9602};
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
			case 9590: //S1
				ret.getAilmentById(1851).getEffects().add(new Ailment.EffectGrouping(60));
				ret.fixMissingAuraAilment(1851, 839, null, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1851, 840, null, Ailment.Target.Party);
				break;
			case 9602: //LD
				ret.getAilmentById(1846).getEffects().add(new Ailment.EffectGrouping(60));
				ret.fixMissingAuraAilment(1846, 841, Ailment.EffectType.E67, Ailment.Target.Party);
				ret.fixMissingAuraAilment(1846, 842, null, Ailment.Target.Party);
			case 9594: //S2
			case 9600: //EX
				ret.getAilmentById(1789).setRank(-1);
				ret.getAilmentById(1789).getEffects().get(1).rankData = new String[] {"-20000010", "-20000015", "-20000020", "-20000025", "-20000030"};
				ret.getAilmentById(1789).getEffects().get(3).rankData = new String[] {"20000010", "20000015", "20000020", "20000025", "20000030"};
				ret.addStaticAilmentEffect(1845, "Triggers ?**" + this.getSpecificAbility(10018).getName() + "**」 when using any HP attack against target with ?**" + this.getSpecificAilment(1789).getName() + "**」");
				break;
		}
		return ret;
	}
}
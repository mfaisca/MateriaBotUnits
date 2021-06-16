package Unit6;
import com.materiabot._Library;
import com.materiabot.GameElements.*;
import com.materiabot.GameElements.Unit;
import com.materiabot.Utils.ImageUtils;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.plugface.core.annotations.Plugin;

@Plugin(name = "Unit.Relm")
public class Relm extends Unit{
	public Relm() {
		super("Relm");
	}
	
	@Override
	public List<Ability> getAbility(Ability.AttackName type, String region) {
		int[] ids = new int[0];
		switch(type) {
			case BRV:
				ids = new int[]{9886};
				break;
			case HP:
				ids = new int[]{8829};
				break;
			case S1:
				ids = new int[]{8822, 100000, 100001, 100002, 100003, 100004, 100005, 100006, 100007, 100008, 100009, 100010, 100011, 100012};
				break;
			case S2:
				ids = new int[]{8825, 8826};
				break;
			case EX:
				ids = new int[]{8828};
				break;
			case AA:
				ids = new int[]{4791};
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
		if(ret == null && id >= 100000) {
			ret = new Ability(id);
			String name = "";
			if(id >= 100002 && _Library.SUMMON_LIST.get(id - 100000).getElement() != null)
				name += ImageUtils.getEmoteText(_Library.SUMMON_LIST.get(id - 100000).getElement().getEmote());
			name += ImageUtils.getEmoteText(_Library.SUMMON_LIST.get(id - 100000).getEmoteCrystal());
			name += _Library.SUMMON_LIST.get(id - 100000).getAttackName();
			ret.setName(name);
			ret.setUseCount(-1);
			ret.setType(Ability.AttackName.S1);
			ret.setUnit(this);
			this.getAbilities().put(id, ret);
		}
		switch(ret.getId()) {
			case 9886: //BRV++
			case 8829: //HP++
				ret.addStaticHit("+50% BRV Damage dealt if target has ?**" + this.getSpecificAilment(466).getName() + "**」", 0);
				break;
			case 8822: //S1
				ret.fixRemoveDispels();
				ret.fixMergeAbility(9740);
				ret.removeAilmentById(533);
				//ret.getAilmentById(136).setFake("ATK Up (during attack)", null, Ailment.Emotes.BUFF_INVISIBLE.get());
				ret.removeAilmentById(136);
				ret.getDetails().getHits().stream().filter(h -> h.getType() == Ability.Details.Hit_Data.AttackName.BRV).forEach(h -> h.setMaxBrvOverflow(150));
				ret.addStaticHit("+100% Attack during this ability", 0);
				ret.addStaticHit("Extra BRV hit or effect based on Summon equipped (see below)", 3);
				break;
			case 8826: //S2+
				ret.fixRemoveDispels();
				ret.fixDelayHitData(10169);
				break;
			case 100000: //Chocobo - Melee Summons
			case 100006: //Brothers
				if(ret.getDetails().getHits().size() == 0) {
					ret.getDetails().setAttackType(Ability.Details.Attack_Type.Melee);
					ret.getDetails().setMovementCost(30);
					ret.getDetails().setChaseDmg(3);
					ret.addStaticHit("BRV Potency: 80% (150% overflow)");
				}
				break;
			case 100002: //Ifrit - Magic Summons
			case 100003: //Shiva
			case 100004: //Shiva
			case 100005: //Leviathan
			case 100008: //Alexander
			case 100009: //Diabolos
			case 100011: //Bahamut
				if(ret.getDetails().getHits().size() == 0) {
					ret.getDetails().setAttackType(Ability.Details.Attack_Type.Magic);
					ret.getDetails().setMovementCost(30);
					ret.getDetails().setChaseDmg(3);
					ret.addStaticHit("BRV Potency: 80% (150% overflow)");
				}
				break;
			case 100001: //Sylph
				if(ret.getDetails().getHits().size() == 0) {
					ret.getDetails().setAttackType(Ability.Details.Attack_Type.Magic);
					ret.getDetails().setMovementCost(30);
					ret.getDetails().setChaseDmg(3);
					ret.addStaticHit("Raises party BRV by 70% of Attack");
					ret.addStaticHit("Recover party HP by 70% of Attack");
				}
				break;
			case 100007: //Pandemonium
				if(ret.getDetails().getHits().size() == 0) {
					ret.getDetails().setAttackType(Ability.Details.Attack_Type.Magic);
					ret.getDetails().setMovementCost(30);
					ret.getDetails().setChaseDmg(3);
					ret.addStaticHit("Easier to initiate a chase sequence (9 [CU](https://www.reddit.com/r/DissidiaFFOO/comments/7x7ffp/chase_mechanic/)))");
					ret.addStaticHit("BRV Potency: 80% (150% overflow)");
				}
				break;
			case 100010: //Odin
				if(ret.getDetails().getHits().size() == 0) {
					ret.getDetails().setAttackType(Ability.Details.Attack_Type.Melee);
					ret.getDetails().setMovementCost(30);
					ret.getDetails().setChaseDmg(3);
					ret.addStaticHit("50% chance to BREAK all enemies");
					ret.addStaticHit("BRV Potency: 80% (150% overflow)");
				}
				break;
			case 100012: //Spirit Mog
				if(ret.getDetails().getHits().size() == 0) {
					ret.getDetails().setAttackType(Ability.Details.Attack_Type.Magic);
					ret.getDetails().setMovementCost(30);
					ret.getDetails().setChaseDmg(3);
					ret.addStaticHit("Removes Terra's debuffs (wherever she is)");
				}
				break;
		}
		return ret;
	}
}
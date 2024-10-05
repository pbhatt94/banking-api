package com.wg.banking.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.wg.banking.constants.BranchConstants;
import com.wg.banking.dao.BranchDAO;
import com.wg.banking.dao.GenericDAO;
import com.wg.banking.model.Branch;

public class BranchDAOImpl extends GenericDAO<Branch> implements BranchDAO {

	public BranchDAOImpl() {
		super(); 
	}

	@Override
	protected Branch mapResultSetToEntity(ResultSet resultSet) throws SQLException {
		Branch branch = new Branch();

		branch.setBranchId(resultSet.getString(BranchConstants.BRANCH_ID_COLUMN));
		branch.setBranchName(resultSet.getString(BranchConstants.BRANCH_NAME_COLUMN));
		branch.setBranchAddress(resultSet.getString(BranchConstants.BRANCH_ADDRESS_COLUMN));
		branch.setBranchManagerId(resultSet.getString(BranchConstants.BRANCH_MANAGER_COLUMN));
		branch.setCreatedAt(resultSet.getDate(BranchConstants.CREATED_AT_COLUMN));
		branch.setUpdatedAt(resultSet.getDate(BranchConstants.UPDATED_AT_COLUMN));

		return branch;
	}

	public Branch getBranchById(String branchId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", BranchConstants.BRANCH_TABLE_NAME,
				BranchConstants.BRANCH_ID_COLUMN, branchId);
		return get(query);
	}

	public Branch getBranchByManagerId(String managerId) throws ClassNotFoundException, SQLException {
		String query = String.format("SELECT * FROM %s WHERE %s = '%s'", BranchConstants.BRANCH_TABLE_NAME,
				BranchConstants.BRANCH_MANAGER_COLUMN, managerId);
		return get(query);
	}

	public boolean addBranch(Branch branch) throws ClassNotFoundException, SQLException {
		String query = String.format(
				"INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
				BranchConstants.BRANCH_TABLE_NAME, BranchConstants.BRANCH_ID_COLUMN, BranchConstants.BRANCH_NAME_COLUMN,
				BranchConstants.BRANCH_ADDRESS_COLUMN, BranchConstants.BRANCH_MANAGER_COLUMN,
				BranchConstants.CREATED_AT_COLUMN, BranchConstants.UPDATED_AT_COLUMN, branch.getBranchId(),
				branch.getBranchName(), branch.getBranchAddress(), branch.getBranchManagerId(),
				new java.sql.Date(branch.getCreatedAt().getTime()), new java.sql.Date(branch.getUpdatedAt().getTime()));

		return update(query);
	}

	public boolean deleteBranch(String branchId) throws ClassNotFoundException, SQLException {
		String query = String.format("DELETE FROM %s WHERE %s = '%s'", BranchConstants.BRANCH_TABLE_NAME,
				BranchConstants.BRANCH_ID_COLUMN, branchId);

		return update(query);
	}

	public List<Branch> getAllBranches() throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM Branch";
		return getAll(query);
	}

	public boolean updateBranch(Branch branch, String columnToUpdate) throws ClassNotFoundException, SQLException {
		String query = String.format("UPDATE %s SET %s = '%s', %s = '%s', %s = '%s' WHERE %s = '%s'",
				BranchConstants.BRANCH_TABLE_NAME, BranchConstants.BRANCH_NAME_COLUMN, branch.getBranchName(),
				BranchConstants.BRANCH_ADDRESS_COLUMN, branch.getBranchAddress(), BranchConstants.BRANCH_MANAGER_COLUMN,
				branch.getBranchManagerId(), BranchConstants.BRANCH_ID_COLUMN, branch.getBranchId());
		return update(query);
	}

}
